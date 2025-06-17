package com.factosback.factos.domain.precedent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.ai.converter.AiConverter;
import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.ai.util.AiClient;
import com.factosback.factos.domain.precedent.converter.PrecedentReplyConverter;
import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.domain.precedent.error.PrecedentErrorCode;
import com.factosback.factos.domain.precedent.model.Precedent;
import com.factosback.factos.domain.precedent.model.PrecedentReply;
import com.factosback.factos.domain.precedent.repository.PrecedentReplyRepository;
import com.factosback.factos.domain.precedent.repository.PrecedentRepository;
import com.factosback.factos.global.config.OpenApiProperties;
import com.factosback.factos.global.error.exception.RestApiException;
import com.factosback.factos.global.util.OpenApiClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PrecedentService {

	private final PrecedentRepository precedentRepository;
	private final PrecedentReplyRepository precedentReplyRepository;
	private final OpenApiClient openApiClient;
	private final AiClient aiClient;
	private final OpenApiProperties openApiProperties;

	// POST용 복합 작업
	@Transactional
	public PrecedentReplyDto.Response getOrCreatePrecedentReply(PrecedentReplyDto.Request request) {
		Optional<Precedent> precedentOpt = precedentRepository.findByCaseNumber(request.getCaseNumber());

		if (precedentOpt.isPresent() && precedentOpt.get().getPrecedentReply() != null) {
			return PrecedentReplyConverter.convertToReplyResponse(
				precedentOpt.get().getPrecedentReply()
			);
		}

		// DTO 기반 API 요청 구성
		PrecedentReplyDto.OpenApiRequest listRequest = PrecedentReplyDto.OpenApiRequest.builder()
			.oc(openApiProperties.oc())
			.target("prec")
			.type(openApiProperties.type())
			.nb(request.getCaseNumber())
			.build();

		List<String> precedentSerialNumberList = openApiClient.getPrecedentIdsByCaseNumber(listRequest);
		if (precedentSerialNumberList.isEmpty()) {
			throw new RestApiException(PrecedentErrorCode.PRECEDENT_NOT_FOUND);
		}

		List<String> aiSummaries = new ArrayList<>();
		for (String serialNumber : precedentSerialNumberList) {

			PrecedentReplyDto.OpenApiRequest contentRequest = PrecedentReplyDto.OpenApiRequest.builder()
				.oc(openApiProperties.oc())
				.target("prec")
				.type(openApiProperties.type())
				.id(serialNumber)  // 판례일련번호
				.build();

			String content = openApiClient.getPrecedentContentById(contentRequest);

			AiRequestDto.PrecedentSummary requestDto = AiConverter.convertToPrecedentSummaryDto(
				request.getCaseNumber(),  // 요청 DTO에서 사건번호 추출
				content
			);

			PrecedentReplyDto.Response aiResponse = aiClient.getPrecedentSummary(requestDto);
			aiSummaries.add(aiResponse.getClaudeResponse());
		}

		Precedent precedent = precedentOpt.orElseGet(() ->
			precedentRepository.save(
				Precedent.builder().caseNumber(request.getCaseNumber()).build()
			)
		);

		PrecedentReply reply = PrecedentReply.builder()
			.precedent(precedent)
			.claudeResponse(String.join("\n", aiSummaries))
			.build();
		precedentReplyRepository.save(reply);

		return PrecedentReplyConverter.convertToReplyResponse(reply);
	}
}

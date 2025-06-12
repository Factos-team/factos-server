package com.factosback.factos.domain.term.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.term.converter.TermConverter;
import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.model.GeneralTerm;
import com.factosback.factos.domain.term.model.TermTranslation;
import com.factosback.factos.domain.term.repository.TermTranslationRepository;
import com.factosback.factos.domain.term.util.OpenApiClient;
import com.factosback.factos.global.config.OpenApiProperties;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TermService {

	private final TermTranslationRepository termTranslationRepository;
	private final OpenApiClient openApiClient;
	private final OpenApiProperties openApiProperties;


	@Transactional
	public TranslateTermDto.Response processTranslation(TranslateTermDto.UserInputRequest request, Object member) {

		String content = request.getContent();
		String legalTerm = extractLegalTerm(content);

		TermTranslation translation = TermTranslation.builder()
			.content(content)
			.legalTerm(legalTerm)
			.member(null)
			.build();

		// API 요청 준비 및 응답
		TranslateTermDto.OpenApiRequest apiRequest = TranslateTermDto.OpenApiRequest.builder()
			.oc(openApiProperties.oc())
			.target(openApiProperties.target())
			.type(openApiProperties.type())
			.query(legalTerm)
			.build();

		List<String> generalTerms = openApiClient.getGeneralTerms(apiRequest);

		// GeneralTerm 객체로 변환 후 저장
		List<GeneralTerm> generalTermEntities = generalTerms.stream()
			.map(gt -> GeneralTerm.builder().generalTerm(gt).build())
			.toList();

		translation.addGeneralTerms(generalTermEntities);
		termTranslationRepository.save(translation);

		return TermConverter.convertToTranslateTermDto(translation);
	}

	// 임시 extractLegalTerm 구현
	// TODO: 실제 추출 로직 구현
	private String extractLegalTerm(String content) {
		return "청원";
	}


}

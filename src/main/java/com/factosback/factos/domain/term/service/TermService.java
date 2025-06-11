package com.factosback.factos.domain.term.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.term.converter.TermConverter;
import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.model.TermTranslation;
import com.factosback.factos.domain.term.repository.TermTranslationRepository;
import com.factosback.factos.domain.term.util.OpenApiClient;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TermService {

	private final TermTranslationRepository termTranslationRepository;
	private final OpenApiClient openApiClient;


	public TranslateTermDto.Response processTranslation(TranslateTermDto.UserInputRequest request, Object member) {

		// 1. 유저 입력 문장을 저장
		TermTranslation termTranslation = TermTranslation.builder()
			.content(request.getContent())
			.legalTerm(null)
			.generalTerm(null)
			.member(null) // 실제 유저 없으므로 null
			.build();

		termTranslationRepository.save(termTranslation);

		// 2. (임시) 법률 용어 추출 로직
		String extractedLegalTerm = extractLegalTerm(request.getContent());

		// 3. OPEN API 호출
		TranslateTermDto.OpenApiRequest openApiRequest = new TranslateTermDto.OpenApiRequest();
		openApiRequest.setOc("test-oc"); // 임시 OC
		openApiRequest.setTarget("lstrmRlt");
		openApiRequest.setType("JSON");
		openApiRequest.setQuery(extractedLegalTerm);

		String generalTerm = openApiClient.getGeneralTerm(openApiRequest);

		// 4. 저장
		termTranslation.updateTerms(extractedLegalTerm, generalTerm);

		return TermConverter.convertToTranslateTermDto(termTranslation);
	}

	private String extractLegalTerm(String content) {
		return "청원"; // TODO: 실제 추출 로직 구현
	}


}

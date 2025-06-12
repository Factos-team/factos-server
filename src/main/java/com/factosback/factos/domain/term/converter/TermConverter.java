package com.factosback.factos.domain.term.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.model.GeneralTerm;
import com.factosback.factos.domain.term.model.TermTranslation;

public class TermConverter {

	/**
	 * openApiClient Converter
	 */
	public static TranslateTermDto.Response convertToTranslateTermDto(TermTranslation termTranslation) {
		List<String> generalTerms = termTranslation.getGeneralTerms().stream()
			.map(GeneralTerm::getGeneralTerm)
			.collect(Collectors.toList());

		return TranslateTermDto.Response.builder()
			.legalTerm(termTranslation.getLegalTerm())
			.generalTerms(generalTerms)
			.context(termTranslation.getContent())
			.build();
	}

	/**
	 * DB 조회 시 여러 값 -> 하나의 DTO로 변환
	 */
	public static TranslateTermDto.Response convertToTranslateTermDto(List<TermTranslation> translations) {
		if (translations == null || translations.isEmpty()) {
			throw new IllegalArgumentException("No translations provided");
		}

		String legalTerm = translations.get(0).getLegalTerm();
		String context = translations.get(0).getContent();

		List<String> generalTerms = translations.stream()
			.flatMap(t -> t.getGeneralTerms().stream())
			.map(GeneralTerm::getGeneralTerm)
			.distinct()
			.collect(Collectors.toList());

		return TranslateTermDto.Response.builder()
			.legalTerm(legalTerm)
			.generalTerms(generalTerms)
			.context(context)
			.build();
	}
}


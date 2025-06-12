package com.factosback.factos.domain.term.converter;

import java.util.stream.Collectors;

import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.model.GeneralTerm;
import com.factosback.factos.domain.term.model.TermTranslation;

public class TermConverter {

	public static TranslateTermDto.Response convertToTranslateTermDto(TermTranslation termTranslation) {
		String generalTerms = termTranslation.getGeneralTerms().stream()
			.map(GeneralTerm::getGeneralTerm)
			.collect(Collectors.joining(", "));

		return TranslateTermDto.Response.builder()
			.legalTerm(termTranslation.getLegalTerm())
			.generalTerm(generalTerms)
			.context(termTranslation.getContent())
			.build();
	}
}


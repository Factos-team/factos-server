package com.factosback.factos.domain.term.converter;

import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.model.TermTranslation;

public class TermConverter {

	public static TranslateTermDto.Response convertToTranslateTermDto(TermTranslation termTranslation) {
		return TranslateTermDto.Response.builder()
			.legalTerm(termTranslation.getLegalTerm())
			.generalTerm(termTranslation.getGeneralTerm())
			.context(termTranslation.getContent())
			.build();
	}
}


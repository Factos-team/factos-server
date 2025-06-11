package com.factosback.factos.domain.precedent.converter;

import com.factosback.factos.domain.precedent.dto.GetPrecedentDto;
import com.factosback.factos.domain.precedent.model.Precedent;

public class PrecedentConverter {

	public static GetPrecedentDto.Response convertToGetPrecedentDto(Precedent precedent) {
		return GetPrecedentDto.Response.builder()
			.title(precedent.getTitle())
			.caseNumber(precedent.getCaseNumber())
			.caseDescription(precedent.getCaseDescription())
			.judgment(precedent.getJudgment())
			.build();
	}
}

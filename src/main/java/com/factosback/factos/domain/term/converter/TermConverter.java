package com.factosback.factos.domain.term.converter;

import com.factosback.factos.domain.term.dto.GetTermDto;
import com.factosback.factos.domain.term.model.Term;

public class TermConverter {

	public static GetTermDto.Response convertToResponse(Term term) {
		return GetTermDto.Response.builder()
			.legalTerm(term.getLegalTerm())
			.claudeResponse(term.getTermReply() != null ? term.getTermReply().getClaudeResponse() : null)
			.build();
	}
}


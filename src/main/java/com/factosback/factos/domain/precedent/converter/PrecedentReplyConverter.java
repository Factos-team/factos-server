package com.factosback.factos.domain.precedent.converter;

import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.domain.precedent.model.PrecedentReply;

public class PrecedentReplyConverter {

	public static PrecedentReplyDto.Response convertToReplyResponse(PrecedentReply precedentReply) {
		return PrecedentReplyDto.Response.builder()
			.claudeResponse(precedentReply.getClaudeResponse())
			.createdAt(precedentReply.getCreatedAt())
			.build();
	}
}
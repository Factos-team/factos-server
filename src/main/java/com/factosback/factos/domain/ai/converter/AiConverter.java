package com.factosback.factos.domain.ai.converter;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.ai.model.AiReply;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.model.ChatMessage;

public class AiConverter {

	public static AiRequestDto convertToAiRequestDto(ChatMessageDto.UserInputRequest request) {
		return AiRequestDto.builder()
			.case_summary(request.getCaseSummary())
			.member_evidence(request.getMemberEvidence())
			.opponent_claim(request.getOpponentClaim())
			.build();
	}

	public static AiReply convertToAiReply(ChatMessageDto.AiResponse response) {
		return AiReply.builder()
			.content(response.getContent())
			.caseNumber(response.getCaseNumber())
			.contextSummary(response.getContextSummary())
			.build();
	}
}

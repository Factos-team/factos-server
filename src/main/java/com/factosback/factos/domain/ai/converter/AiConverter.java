package com.factosback.factos.domain.ai.converter;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.ai.model.AiReply;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.model.ChatMessage;

public class AiConverter {

	// public static AiRequestDto convertToAiRequestDto(ChatMessageDto.UserInputRequest request, String contextSummary) {
	// 	return AiRequestDto.builder()
	// 		.userInput(request.getUserInput())
	// 		.contextSummary(contextSummary)
	// 		.build();
	// }

	public static AiReply convertToChatResponseDto(ChatMessageDto.AiResponse response) {
		return AiReply.builder()
			.claudeResponse(response.getClaudeResponse())
			.caseNumber(response.getCaseNumber())
			.contextSummary(response.getContextSummary())
			.build();
	}

	public static AiRequestDto.ChatResponse convertToChatRequestDto(ChatMessageDto.UserInputRequest request, String contextSummary) {
		return AiRequestDto.ChatResponse.builder()
			.userInput(request.getUserInput())
			.contextSummary(contextSummary)
			.build();
	}

	public static AiRequestDto.PrecedentSummary convertToPrecedentSummaryDto(String caseNumber, String precedentContent) {
		return AiRequestDto.PrecedentSummary.builder()
			.caseNumber(caseNumber)
			.precedentContent(precedentContent)
			.build();
	}

	public static AiRequestDto.TermExplanation convertToTermExplanationDto(String legalTerm, String context) {
		return AiRequestDto.TermExplanation.builder()
			.legalTerm(legalTerm)
			.context(context)
			.build();
	}
}

package com.factosback.factos.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatMessageDto {

	@Getter
	@NoArgsConstructor
	public static class UserInputRequest {
		private String userInput;
	}

	@Getter
	@Builder
	public static class AiResponse {
		private String claudeResponse;
		private String caseNumber;
		private String contextSummary;
	}
}

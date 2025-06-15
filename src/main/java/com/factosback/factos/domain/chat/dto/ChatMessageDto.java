package com.factosback.factos.domain.chat.dto;

import java.util.List;

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
		private List<String> caseNumber;
		private String contextSummary;
	}
}

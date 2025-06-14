package com.factosback.factos.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatMessageDto {

	@Getter
	@NoArgsConstructor
	public static class UserInputRequest {
		private Long chatRoomId;
		private String caseSummary;
		private String memberEvidence;
		private String opponentClaim;
	}

	@Getter
	@Builder
	public static class AiResponse {
		private String content;
		private Integer caseNumber;
		private String contextSummary;
	}
}

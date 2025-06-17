package com.factosback.factos.domain.ai.dto;

import lombok.Builder;
import lombok.Getter;

public class AiRequestDto {

	// 채팅 응답 요청용 DTO
	@Getter
	@Builder
	public static class ChatResponse {
		private String userInput;
		private String contextSummary;
	}

	// 판례 요약 요청용 DTO
	@Getter
	@Builder
	public static class PrecedentSummary {
		private String caseNumber;
		private String precedentContent;
	}

	// 용어 설명 요청용 DTO
	@Getter
	@Builder
	public static class TermExplanation {
		private String legalTerm;
		private String claudeResponse;
	}
}

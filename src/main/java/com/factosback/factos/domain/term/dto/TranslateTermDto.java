package com.factosback.factos.domain.term.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TranslateTermDto {

	@Getter
	@NoArgsConstructor
	public static class UserInputRequest {
		private String content;
	}

	/**
	 * 클라이언트가 보낸 요청 받는 DTO X
	 * 외부 API 호출하기 위한 객체
	 *
	 */
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OpenApiRequest {
		private String oc;
		private String target;
		private String type;
		private String query;
	}

	@Getter
	@Builder
	public static class Response {
		private String legalTerm;
		private String generalTerm;
		private String context;
	}
}

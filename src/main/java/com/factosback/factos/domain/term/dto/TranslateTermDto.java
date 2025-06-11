package com.factosback.factos.domain.term.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TranslateTermDto {

	@Getter
	@NoArgsConstructor
	public static class UserInputRequest {
		private String content;
	}

	@Getter
	@Setter
	@NoArgsConstructor
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

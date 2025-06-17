package com.factosback.factos.domain.term.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GetTermDto {

	@Getter
	@NoArgsConstructor
	public static class Request {
		private String legalTerm;
	}

	@Getter
	@Builder
	public static class Response {
		private String legalTerm;
		private String claudeResponse;
	}
}

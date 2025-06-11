package com.factosback.factos.domain.precedent.dto;

import lombok.Builder;
import lombok.Getter;

public class GetPrecedentDto {

	@Builder
	@Getter
	public static class Response {

		private Integer caseNumber;
		private String title;
		private String caseDescription;
		private String judgment;
	}
}

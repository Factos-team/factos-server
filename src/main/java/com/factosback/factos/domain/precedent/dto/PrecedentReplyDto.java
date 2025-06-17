package com.factosback.factos.domain.precedent.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PrecedentReplyDto {

	@Getter
	@Builder
	@AllArgsConstructor
	public static class Request  {
		private String caseNumber;
	}

	@Getter
	@Builder
	public static class Response {
		private String claudeResponse;
		private LocalDateTime createdAt;
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OpenApiRequest {
		private String oc;
		private String target;
		private String type;
		private String nb;     // 목록 조회용 사건번호
		private String id;     // 본문 조회용 판례일련번호
	}
}

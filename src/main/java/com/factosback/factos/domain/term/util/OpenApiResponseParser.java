package com.factosback.factos.domain.term.util;

import org.springframework.stereotype.Component;

@Component
public class OpenApiResponseParser {

	public String extractGeneralTerm(String responseBody) {
		// TODO: 실제 파싱 로직 구현
		// ex) JSON이면 JSON 파싱, XML이면 XPath 등
		// 지금은 임시로 문자열 포함 여부로 처리
		if (responseBody.contains("청원")) {
			return "청원";
		}
		return "알 수 없음";
	}
}

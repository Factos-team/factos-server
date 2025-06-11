package com.factosback.factos.domain.term.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OpenApiResponseParser {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public String extractGeneralTerm(String responseBody) {
		try {
			JsonNode root = objectMapper.readTree(responseBody);

			// "일상용어명"이 배열 안 객체 속성일 수 있는 경우를 대비해 반복 처리
			// ex) { items: [ { "일상용어명": "약속" }, {...} ] }
			if (root.has("items") && root.get("items").isArray()) {
				for (JsonNode item : root.get("items")) {
					if (item.has("일상용어명")) {
						return item.get("일상용어명").asText();
					}
				}
			}

			// 혹시 items 배열 없이 바로 있는 경우
			if (root.has("일상용어명")) {
				return root.get("일상용어명").asText();
			}
		} catch (Exception e) {
			log.warn("JSON 파싱 오류: " + e.getMessage());
		}
		return "알 수 없음";
	}
}

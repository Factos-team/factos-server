package com.factosback.factos.domain.term.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OpenApiResponseParser {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public List<String> extractGeneralTerms(String responseBody) {
		List<String> results = new ArrayList<>();
		try {
			JsonNode root = objectMapper.readTree(responseBody);
			JsonNode serviceNode = root.path("lstrmRltService");

			JsonNode items = serviceNode.path("법령용어").path("연계용어");

			if (items.isArray()) {
				for (JsonNode item : items) {
					JsonNode termNode = item.get("일상용어명");
					if (termNode != null && !termNode.isNull()) {
						results.add(termNode.asText().trim());
					}
				}
			}
		} catch (Exception e) {
			log.warn("OpenAPI 응답 파싱 실패", e);
		}
		return results;
	}
}

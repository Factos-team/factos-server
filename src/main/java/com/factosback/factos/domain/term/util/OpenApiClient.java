package com.factosback.factos.domain.term.util;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenApiClient {

	private final WebClient openApiWebClient;

	public String getGeneralTerm(String oc, String target, String type, String query) {
		return openApiWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/lawService.do")
				.queryParam("OC", oc)
				.queryParam("target", target)
				.queryParam("type", type)
				.queryParam("query", query)
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.map(response -> "일상용어 예시") // TODO: 파싱 로직으로 교체
			.block();
	}
}

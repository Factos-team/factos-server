package com.factosback.factos.domain.term.util;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.factosback.factos.domain.term.dto.TranslateTermDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenApiClient {

	private final WebClient openApiWebClient;
	private final OpenApiResponseParser openApiResponseParser;

	public List<String> getGeneralTerms(TranslateTermDto.OpenApiRequest request) {
		return openApiWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/lawService.do")
				.queryParam("OC", request.getOc())
				.queryParam("target", request.getTarget())
				.queryParam("type", request.getType())
				.queryParam("query", request.getQuery())
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.map(openApiResponseParser::extractGeneralTerms)
			.block();
	}
}

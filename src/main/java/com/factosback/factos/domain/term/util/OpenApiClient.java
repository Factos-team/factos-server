package com.factosback.factos.domain.term.util;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.global.error.code.CommonErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
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
			.onStatus(HttpStatusCode::isError, response -> {
				return response.bodyToMono(String.class)
					.flatMap(body -> {
						log.error("API 오류 응답: {}", body);
						return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
					});
			})
			.bodyToMono(String.class)
			.map(openApiResponseParser::extractGeneralTerms)
			.block();
	}
}

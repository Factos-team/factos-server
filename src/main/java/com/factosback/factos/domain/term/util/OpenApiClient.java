package com.factosback.factos.domain.term.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

		// OC 값 대소문자 일치 확인
		String oc = request.getOc();
		log.info("OC 값(대소문자 포함): {}", oc);

		// 쿼리 인코딩 확인
		String encodedQuery = URLEncoder.encode(request.getQuery(), StandardCharsets.UTF_8);
		log.info("원본 쿼리: {}, 인코딩된 쿼리: {}", request.getQuery(), encodedQuery);

		// 최종 요청 URI 로그
		String finalUri = String.format(
			"https://www.law.go.kr/DRF/lawService.do?OC=%s&target=%s&type=%s&query=%s",
			oc, request.getTarget(), request.getType(), encodedQuery
		);
		log.info("최종 요청 URI: {}", finalUri);

		return openApiWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/lawService.do")
				.queryParam("OC", request.getOc())
				.queryParam("target", request.getTarget())
				.queryParam("type", request.getType())
				.queryParam("query", URLEncoder.encode(request.getQuery(), StandardCharsets.UTF_8))
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
			// .map(response -> {
			// 	log.info("API Raw Response: {}\n", response);
			// 	return response;
			// })
			.doOnNext(response -> log.info("API Raw Response: {}", response))
			.map(openApiResponseParser::extractGeneralTerms)
			.block();
	}
}

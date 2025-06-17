package com.factosback.factos.global.util;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenApiClient {

	private final WebClient openApiWebClient;
	private final OpenApiResponseParser openApiResponseParser;

	/**
	 * 판례 목록 조회 API
	 */
	public List<String> getPrecedentIdsByCaseNumber(PrecedentReplyDto.OpenApiRequest request) {

		String response = openApiWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/DRF/lawSearch.do")
				.queryParam("OC", request.getOc())
				.queryParam("target", request.getTarget())
				.queryParam("type", request.getType())
				.queryParam("nb", request.getNb())
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.doOnSubscribe(sub -> log.info("[OpenApiClient] 목록조회 API 호출 시작"))
			.doOnSuccess(body -> log.debug("[OpenApiClient] 목록조회 응답: {}", body))
			.doOnError(e -> log.error("[OpenApiClient] 목록조회 오류", e))
			.block();

		// JSON 파싱 후 판례일련번호 리스트 추출
		return OpenApiResponseParser.extractPrecedentIds(response);
	}

	/**
	 * 판례 본문 조회 API
	 */
	public String getPrecedentContentById(PrecedentReplyDto.OpenApiRequest request) {

		String response = openApiWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/DRF/lawService.do")
				.queryParam("OC", request.getOc())
				.queryParam("target", request.getTarget())
				.queryParam("type", request.getType())
				.queryParam("ID", request.getId())
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.doOnSubscribe(sub -> log.info("[OpenApiClient] 본문조회 API 호출 시작"))
			.doOnSuccess(body -> log.debug("[OpenApiClient] 본문조회 응답: {}", body))
			.doOnError(e -> log.error("[OpenApiClient] 본문조회 오류", e))
			.block();

		// JSON 파싱 후 판례 본문 추출
		return OpenApiResponseParser.extractPrecedentContent(response);
	}
}

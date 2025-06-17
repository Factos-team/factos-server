package com.factosback.factos.global.util;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.global.config.OpenApiProperties;
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

	/**
	 * 판례 목록 조회 API
	 */
	public List<String> getPrecedentIdsByCaseNumber(PrecedentReplyDto.OpenApiRequest request) {
		log.debug("[OpenApiClient] 목록조회 요청 파라미터: OC={}, target={}, type={}, nb={}",
			request.getOc(), request.getTarget(), request.getType(), request.getNb());

		String url = "/DRF/lawSearch.do?OC=" + request.getOc() +
			"&target=" + request.getTarget() +
			"&type=" + request.getType() +
			"&nb=" + request.getNb();
		log.info("[OpenApiClient] 목록조회 실제 호출 URL: {}{}", openApiWebClient, url);

		// 목록조회 API 호출
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
		log.debug("[OpenApiClient] 본문조회 요청 파라미터: OC={}, target={}, type={}, ID={}",
			request.getOc(), request.getTarget(), request.getType(), request.getId());

		String url = "/DRF/lawService.do?OC=" + request.getOc() +
			"&target=" + request.getTarget() +
			"&type=" + request.getType() +
			"&ID=" + request.getId();
		log.info("[OpenApiClient] 본문조회 실제 호출 URL: {}{}", openApiWebClient, url);

		// 본문조회 API 호출
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

	/**
	 * 용어 변환 조회 API
	 */
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

package com.factosback.factos.domain.ai.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.global.error.code.CommonErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

// @Profile("!local")
@Slf4j
@Component
@RequiredArgsConstructor
public class AiClient {

	private final WebClient aiWebClient;

	@Value("${ai.endpoint.chat}")
	private String chatEndpoint;

	@Value("${ai.endpoint.precedent}")
	private String precedentEndpoint;

	@Value("${ai.endpoint.term}")
	private String termEndpoint;

	/**
	 * 채팅 응답 생성
	 */
	public ChatMessageDto.AiResponse getChatResponse(AiRequestDto.ChatResponse request) {
		return aiWebClient.post()
			.uri(chatEndpoint)
			.bodyValue(request)
			.retrieve()
			.onStatus(HttpStatusCode::isError, response ->
				response.bodyToMono(String.class)
					.flatMap(body -> {
						log.error("[Chat] AI API 오류 | 요청: {} | 응답: {}", request.getUserInput(), body);
						return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
					})
			)
			.bodyToMono(ChatMessageDto.AiResponse.class)
			.doOnSuccess(res -> log.info("[Chat] 분석 성공 | 사건번호: {}", res.getCaseNumber()))
			.doOnError(e -> log.error("[Chat] 통신 실패: {}", e.getMessage()))
			.block();
	}

	/**
	 * 판례 요약 생성
	 */
	public PrecedentReplyDto.Response getPrecedentSummary(AiRequestDto.PrecedentSummary request) {
		return aiWebClient.post()
			.uri(precedentEndpoint)
			.bodyValue(request)
			.retrieve()
			.onStatus(HttpStatusCode::isError, response ->
				response.bodyToMono(String.class)
					.flatMap(body -> {
						log.error("[Precedent] AI API 오류 | 사건번호: {} | 응답: {}",
							request.getCaseNumber(), body);
						return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
					})
			)
			.bodyToMono(PrecedentReplyDto.Response.class)
			.doOnSuccess(res -> log.info("[Precedent] 분석 성공 | 사건번호: {}", request.getCaseNumber()))
			.doOnError(e -> log.error("[Precedent] 통신 실패: {}", e.getMessage()))
			.block();
	}

	/**
	 * 법률 용어 설명 생성
	 */
	public PrecedentReplyDto.Response getTermExplanation(AiRequestDto.TermExplanation request) {
		log.debug("AI 서버에 보낼 요청: {}", request);
		PrecedentReplyDto.Response response = aiWebClient.post()
			.uri(termEndpoint)
			.bodyValue(request)
			.retrieve()
			.onStatus(HttpStatusCode::isError, r -> {
				log.error("AI API 오류: status={}, request={}", r.statusCode(), request);
				return r.bodyToMono(String.class).flatMap(body -> {
					log.error("AI API 오류 응답 body: {}", body);
					return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
				});
			})
			.bodyToMono(PrecedentReplyDto.Response.class)
			.doOnSuccess(res -> log.info("AI 응답 성공: {}", res))
			.doOnError(e -> log.error("AI 통신 실패: {}", e.getMessage()))
			.block();
		log.debug("최종 AI 응답: {}", response);
		return response;
	}
}

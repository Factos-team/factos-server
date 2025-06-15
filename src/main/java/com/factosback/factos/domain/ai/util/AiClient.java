package com.factosback.factos.domain.ai.util;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.global.error.code.CommonErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AiClient {

	private final WebClient aiWebClient;

	public ChatMessageDto.AiResponse getAiReply(AiRequestDto request) {
		return aiWebClient.post()
			.uri("임시uri예시")
			.bodyValue(request)
			.retrieve()
			.onStatus(HttpStatusCode::isError, response ->
				response.bodyToMono(String.class)
					.flatMap(body -> {
						log.error("AI API 오류 응답: {} | 요청 내용: {}", body, request.getUserInput());
						return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
					})
			)
			.bodyToMono(ChatMessageDto.AiResponse.class)
			.doOnSuccess(res -> log.info("AI 분석 성공 | 사건번호: {} | 문맥요약: {}", res.getCaseNumber(), res.getContextSummary()))
			.doOnError(e -> log.error("AI 통신 실패: {}", e.getMessage()))
			.block();
	}
}

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
						log.error("AI 응답 오류: {}", body);
						return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
					})
			)
			.bodyToMono(ChatMessageDto.AiResponse.class)
			.block();
	}
}

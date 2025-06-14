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
		// AI 파트 기능 구현 전까지 Mock 데이터 테스트
		return ChatMessageDto.AiResponse.builder()
			.content("AI 분석 결과: 계약 위반 가능성이 높습니다. 이 사건은 계약서의 해석이 핵심입니다. ")
			.caseNumber(12345)
			.contextSummary("계약 위반 여부와 해석을 중심으로 판단해야 합니다.")
			.build();

		/**
		 * 기존 코드
		 */
		// return aiWebClient.post()
		// 	.uri("임시uri예시")
		// 	.bodyValue(request)
		// 	.retrieve()
		// 	.onStatus(HttpStatusCode::isError, response ->
		// 		response.bodyToMono(String.class)
		// 			.flatMap(body -> {
		// 				log.error("AI 응답 오류: {}", body);
		// 				return Mono.error(new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR));
		// 			})
		// 	)
		// 	.bodyToMono(ChatMessageDto.AiResponse.class)
		// 	.block();
	}
}

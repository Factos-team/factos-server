package com.factosback.factos.domain.ai.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;

@Profile("local")
@Component
public class MockAiClient extends AiClient {

	public MockAiClient() {
		super(null);
	}

	@Override
	public ChatMessageDto.AiResponse getAiReply(AiRequestDto request) {
		// 요청 userInput에 따라 임의 응답 반환
		if (request.getUserInput().contains("술집에서 술마시다가")) {
			return ChatMessageDto.AiResponse.builder()
				.claudeResponse("[{'type': 'text', 'text': '사용자의 상황은 술집에서 옆사람과의 시비로 인한 폭행 사건으로 보입니다. ... 무혐의 판결을 받을 수 있을 것으로 예상됩니다.'}]")
				.caseNumber("[{'doc_id': '94도2638'}, {'doc_id': '86도1075'}, {'doc_id': '80노2214'}, {'doc_id': '86도555'}, {'doc_id': '95도852'}]")
				.contextSummary("사용자가 술집에서 옆 사람과 시비가 붙어 폭행 사건이 발생한 것으로 보입니다. ...")
				.build();
		} else if (request.getUserInput().contains("기억이 끊겨 CCTV를 더 자세히 확인해 본 결과")) {
			return ChatMessageDto.AiResponse.builder()
				.claudeResponse("[{'type': 'text', 'text': '이 사례에서 사용자의 상황은 술집에서 상대방과 시비가 붙어 폭행 사건이 발생한 것으로 보이며 ... 정당방위 주장이 받아들여질 가능성이 높습니다.'}]")
				.caseNumber("[{'doc_id': '84도488'}, {'doc_id': '91도3172'}, {'doc_id': '86도555'}, {'doc_id': '86노334'}, {'doc_id': '94도1905'}]")
				.contextSummary("이 사례의 핵심은 술집에서 사용자와 상대방이 다툼 끝에 폭력 사건이 발생하였으나 ...")
				.build();
		}
		// 기본 응답
		return ChatMessageDto.AiResponse.builder()
			.claudeResponse("[{'type': 'text', 'text': 'Mock 응답입니다.'}]")
			.caseNumber("[]")
			.contextSummary("Mock context summary")
			.build();
	}
}

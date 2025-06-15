package com.factosback.factos.domain.ai.util;

import java.util.List;

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
				.claudeResponse("""
                    사용자의 상황은 술집에서 옆사람과의 시비로 인한 폭행 사건으로 보입니다. 
                    유사 판례 [80노2214]와 [95도852]에서 정당방위가 인정된 사례를 참고할 때,
                    CCTV 영상 확인 결과 상대방이 먼저 공격한 것으로 판단되어 무혐의 처리될 가능성이 높습니다.
                    """)
				.caseNumber(List.of("80노2214", "86도1075", "95도852", "86고단221", "86도555"))
				.contextSummary("""
                    술집 시비 상황에서의 정당방위 가능성 확인.
                    CCTV 분석 결과를 바탕으로 형사처벌 면할 것으로 예상됨.
                    """)
				.build();
		} else if (request.getUserInput().contains("기억이 끊겨 CCTV를 더 자세히 확인해 본 결과")) {
			return ChatMessageDto.AiResponse.builder()
				.claudeResponse("""
                    CCTV 추가 확인 결과, 사용자의 행위가 상대방의 공격에 대한 방어적 측면이 강하게 나타남.
                    [86도555] 판례와 유사하게 정당방위 성립 가능성이 높다고 판단됩니다.
                    """)
				.caseNumber(List.of("84도488", "91도3172", "86도555", "86노334", "94도1905"))
				.contextSummary("""
                    기억 소실 구간에 대한 CCTV 추가 분석 완료.
                    방어 행위의 적절성 입증 가능성 확인.
                    """)
				.build();
		}
		// 기본 응답
		return ChatMessageDto.AiResponse.builder()
			.claudeResponse("Mock 응답입니다.")
			.caseNumber(List.of())
			.contextSummary("Mock context summary")
			.build();
	}
}

// package com.factosback.factos.domain.precedent.util;
//
// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Component;
//
// import com.factosback.factos.domain.ai.util.AiClient;
//
// @Profile("local")
// @Component
// public class MockPrecedentAiClient extends AiClient {
//
// 	public MockPrecedentAiClient() {
// 		super(null);
// 	}
//
// 	@Override
// 	public String getPrecedentContent(String precedentContent) {
// 		// precedentContent에 따라 임의 응답 반환
// 		if (precedentContent != null && precedentContent.contains("부전지")) {
// 			// 실제 AI 응답 형식에 맞춰 explanation 필드만 추출
// 			return """
//                 알겠습니다. 법률 전문용어 "부전지"에 대해 설명드리겠습니다.
//
//                 "부전지"는 법률 용어로, 어떤 토지나 건물의 맨 아래쪽에 위치한 땅을 말합니다. 즉, 주된 건물이나 토지의 아랫부분에 있는 작은 땅을 뜻하는 용어입니다.
//
//                 예를 들어, 어떤 집이 있는데 그 집의 맨 아래쪽에 작은 땅이 있다면 그 땅을 "부전지"라고 합니다. 또한 큰 빌딩의 밑바닥에 있는 작은 토지도 "부전지"에 해당됩니다.
//
//                 "부전지"와 비슷한 의미의 일상용어로는 "기반 토지", "기초 땅", "밑바닥 땅" 등이 있습니다.
//
//                 예를 들어 "그 큰 빌딩의 밑바닥에 있는 부전지를 건물 주인이 매입했다"라는 문장을 일상용어로 바꾸면 "그 큰 빌딩의 기반 토지를 건물 주인이 사들였다"라고 할 수 있습니다.
//                 """;
// 		}
// 		// 기본 mock 응답
// 		return "Mock 부전지 설명입니다.";
// 	}
// }

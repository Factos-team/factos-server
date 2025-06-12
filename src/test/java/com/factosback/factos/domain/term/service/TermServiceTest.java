package com.factosback.factos.domain.term.service;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class TermServiceTest {

	@Test
	void processTranslation() {
	}

	@Test
	void compareGeneratedUrlWithPostman() throws Exception {
		// given: Postman에서 성공한 URL과 동일한 파라미터
		String oc = "myvalue";
		String target = "lstrmRlt";
		String type = "JSON";
		String query = "청원";

		// when: WebClient가 생성하는 URL 시뮬레이션
		String encodedQuery = query;
		String generatedUrl = String.format(
			"https://www.law.go.kr/DRF/lawService.do?OC=%s&target=%s&type=%s&query=%s",
			oc, target, type, encodedQuery
		);

		// then: Postman URL과 비교
		String postmanUrl = "https://www.law.go.kr/DRF/lawService.do?OC=myvalue&target=lstrmRlt&type=JSON&query=청원";

		// URL 구성 요소 분해 비교
		assertUriComponentsEquals(postmanUrl, generatedUrl);
	}

	private void assertUriComponentsEquals(String expected, String actual) {
		String[] expectedParts = expected.split("\\?");
		String[] actualParts = actual.split("\\?");

		// 기본 경로 비교
		assertEquals(expectedParts[0], actualParts[0], "Base URL 불일치");

		// 쿼리 파라미터 분해 비교
		String[] expectedParams = expectedParts[1].split("&");
		String[] actualParams = actualParts[1].split("&");

		assertEquals(expectedParams.length, actualParams.length, "파라미터 개수 불일치");

		for (int i = 0; i < expectedParams.length; i++) {
			String[] expectedPair = expectedParams[i].split("=");
			String[] actualPair = actualParams[i].split("=");

			assertEquals(expectedPair[0], actualPair[0], "파라미터 키 불일치: " + expectedPair[0]);
			assertEquals(expectedPair[1], actualPair[1], "파라미터 값 불일치: " + expectedPair[0]);
		}
	}
}
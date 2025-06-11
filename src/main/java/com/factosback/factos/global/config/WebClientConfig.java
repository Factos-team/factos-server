package com.factosback.factos.global.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

	/**
	 * 참고 webClient 기본 base code
	 */
	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
			.baseUrl("호출할 API 서비스 도메인 URL")
			.defaultHeaders(httpHeaders -> {
				httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
				httpHeaders.add("apiKey", "API Key 값 입력");
			})
			// 필요한 설정 추가
			.build();
	}

	@Bean
	public WebClient openApiWebClient(WebClient.Builder builder) {
		/**
		 * Request parameter
		 * 필수 값 OC, target, type, query
		 * 요청 URL https://www.law.go.kr/DRF/lawService.do?target=lstrmRlt
		 * ex) https://www.law.go.kr/DRF/lawService.do?OC=test&target=lstrmRlt&type=XML&query=청원
		 */
		return builder
			.baseUrl("https://www.law.go.kr/DRF")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			//.defaultUriVariables(Map.of("oc", "내 OC값"))
			.build();
	}
}

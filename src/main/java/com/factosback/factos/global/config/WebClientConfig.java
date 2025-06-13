package com.factosback.factos.global.config;

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
	public WebClient openApiWebClient(WebClient.Builder builder, OpenApiProperties openApiProperties) {
		return builder
			.baseUrl(openApiProperties.baseUrl())
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.codecs(configurer -> configurer
				.defaultCodecs()
				.maxInMemorySize(16 * 1024 * 1024))
			.build();
	}

	@Bean
	public WebClient aiWebClient(WebClient.Builder builder, AiProperties aiProperties) {
		return builder
			.baseUrl(aiProperties.baseUrl())
			.defaultHeaders(httpHeaders -> {
				httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
				// httpHeaders.add("Authorization", "Bearer " + aiProperties.apiKey());
			})
			.codecs(configurer -> configurer
				.defaultCodecs()
				.maxInMemorySize(16 * 1024 * 1024))
			.build();
	}
}

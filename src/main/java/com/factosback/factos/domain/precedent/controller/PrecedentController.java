package com.factosback.factos.domain.precedent.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.domain.precedent.service.PrecedentService;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/precedents")
public class PrecedentController {

	private final PrecedentService precedentService;

	// 신규 POST 엔드포인트 (AI 응답 생성 포함)
	@PostMapping("/search")
	public ApiResponse<PrecedentReplyDto.Response> searchPrecedent(
		@RequestBody PrecedentReplyDto.Request request
	) {
		PrecedentReplyDto.Response response = precedentService.getOrCreatePrecedentReply(request);
		return ApiResponse.createSuccess(response);
	}
}

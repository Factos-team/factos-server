package com.factosback.factos.domain.term.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.service.TermService;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/terms")
public class TermController {

	private final TermService termService;

	@PostMapping("/translate")
	public ApiResponse<TranslateTermDto.Response> translateTerm(@RequestBody TranslateTermDto.UserInputRequest request) {

		// 현재 로그인 미구현 상태이므로 Mock 처리
		TranslateTermDto.Response response = termService.processTranslation(request, null);

		return ApiResponse.createSuccess(response);
	}
}

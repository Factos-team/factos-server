package com.factosback.factos.domain.term.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factosback.factos.domain.term.dto.GetTermDto;
import com.factosback.factos.domain.term.service.TermService;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/terms")
public class TermController {

	private final TermService termService;

	@PostMapping("/translate")
	public ApiResponse<GetTermDto.Response> translateTerm(@RequestBody GetTermDto.Request request) {
		return ApiResponse.createSuccess(termService.processGetTerm(request));
	}
}

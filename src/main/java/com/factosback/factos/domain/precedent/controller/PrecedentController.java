package com.factosback.factos.domain.precedent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factosback.factos.domain.precedent.dto.GetPrecedentDto;
import com.factosback.factos.domain.precedent.service.PrecedentService;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/precedents")
public class PrecedentController {

	private final PrecedentService precedentService;

	@GetMapping("/{caseNumber}")
	public ApiResponse<GetPrecedentDto.Response> getPrecedents(@PathVariable Integer caseNumber) {

		GetPrecedentDto.Response response = precedentService.getPrecedent(caseNumber);

		return ApiResponse.createSuccess(response);
	}
}

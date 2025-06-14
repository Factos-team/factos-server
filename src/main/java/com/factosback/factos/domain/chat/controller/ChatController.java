package com.factosback.factos.domain.chat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.service.ChatService;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	private final ChatService chatService;

	@PostMapping("/send")
	public ApiResponse<ChatMessageDto.AiResponse> processChatMessage(@RequestBody ChatMessageDto.UserInputRequest request) {

		ChatMessageDto.AiResponse response = chatService.processChatMessage(request).getData();

		return ApiResponse.createSuccess(response);
	}
}

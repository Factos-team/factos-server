package com.factosback.factos.domain.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.ai.converter.AiConverter;
import com.factosback.factos.domain.ai.model.AiReply;
import com.factosback.factos.domain.ai.util.AiClient;
import com.factosback.factos.domain.chat.converter.ChatConverter;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.model.ChatMessage;
import com.factosback.factos.domain.chat.repository.ChatMessageRepository;
import com.factosback.factos.domain.chat.repository.ChatRoomRepository;
import com.factosback.factos.domain.member.model.Member;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

	private final ChatRoomRepository chatRoomRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final AiClient aiClient;

	@Transactional
	public ApiResponse<ChatMessageDto.AiResponse> processChatMessage(
		ChatMessageDto.UserInputRequest request,
		Member member
	) {
		// 1. 채팅 메시지 저장
		ChatMessage chatMessage = chatMessageRepository.save(
			ChatConverter.convertToChatMessage(request, member)
		);

		// 2. AI 분석 요청
		ChatMessageDto.AiResponse aiResponse = aiClient.getAiAnalysis(
			AiConverter.convertToAiRequestDto(request)
		);

		// 3. AiReply 생성 및 연관관계 설정
		AiReply aiReply = AiConverter.convertToAiReply(aiResponse);
		chatMessage.addAiReply(aiReply); // 양방향 연관관계 연결

		chatMessageRepository.save(chatMessage);

		return ApiResponse.createSuccess(aiResponse);
	}
}

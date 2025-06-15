package com.factosback.factos.domain.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.ai.converter.AiConverter;
import com.factosback.factos.domain.ai.model.AiReply;
import com.factosback.factos.domain.ai.util.AiClient;
import com.factosback.factos.domain.chat.converter.ChatConverter;
import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.error.ChatErrorCode;
import com.factosback.factos.domain.chat.model.ChatMessage;
import com.factosback.factos.domain.chat.model.ChatRoom;
import com.factosback.factos.domain.chat.repository.ChatMessageRepository;
import com.factosback.factos.domain.chat.repository.ChatRoomRepository;
import com.factosback.factos.domain.member.model.Member;
import com.factosback.factos.global.error.exception.RestApiException;
import com.factosback.factos.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

	private final ChatRoomRepository chatRoomRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final AiClient aiClient;

	@Transactional
	public ApiResponse<ChatMessageDto.AiResponse> processChatMessage(
		Long chatRoomId, ChatMessageDto.UserInputRequest request
	) {

		String prevContextSummary = getPreviousContext(chatRoomId);

		// 1. AI 분석 요청
		ChatMessageDto.AiResponse aiResponse = aiClient.getAiReply(
			AiConverter.convertToAiRequestDto(request, prevContextSummary)
		);

		// 2. 채팅방 가져오기 (현재 테스트 1L)
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
			.orElseThrow(() -> new RestApiException(ChatErrorCode.CHATROOM_NOT_FOUND));

		// 3. ChatMessage, AiReply 저장
		ChatMessage chatMessage = ChatConverter.convertToChatMessage(request, chatRoom);
		AiReply aiReply = AiConverter.convertToAiReply(aiResponse);
		chatMessage.addAiReply(aiReply);

		chatMessageRepository.save(chatMessage);

		return ApiResponse.createSuccess(aiResponse);
	}

	/**
	 * 컨텍스트 조회
	 */
	private String getPreviousContext(Long chatRoomId) {
		return chatMessageRepository.findLatestByChatRoomId(chatRoomId)
			.map(ChatMessage::getAiReply)
			.map(AiReply::getContextSummary)
			.orElse("");

		// String context = chatMessageRepository.findLatestByChatRoomId(chatRoomId)
		// 	.map(ChatMessage::getAiReply)
		// 	.map(AiReply::getContextSummary)
		// 	.orElse("");
		//
		// // 컨텍스트 조회 로그 추가
		// log.info("이전 컨텍스트 조회 | 채팅방 ID: {} | 컨텍스트: {}", chatRoomId,
		// 	context.isEmpty() ? "첫 대화" : context.substring(0, Math.min(20, context.length())) + "...");
		//
		// return context;
	}
}

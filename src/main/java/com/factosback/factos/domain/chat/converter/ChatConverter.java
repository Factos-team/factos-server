package com.factosback.factos.domain.chat.converter;

import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.model.ChatMessage;
import com.factosback.factos.domain.chat.model.ChatRoom;

public class ChatConverter {

	public static ChatMessage convertToChatMessage(ChatMessageDto.UserInputRequest request, ChatRoom chatRoom) {

		return ChatMessage.builder()
			.chatRoom(chatRoom)
			.userInput(request.getUserInput())
			.build();
	}
}

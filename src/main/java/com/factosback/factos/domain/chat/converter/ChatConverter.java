package com.factosback.factos.domain.chat.converter;

import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.model.ChatMessage;
import com.factosback.factos.domain.chat.model.ChatRoom;
import com.factosback.factos.domain.chat.model.SenderStatus;

public class ChatConverter {

	public static ChatMessage convertToChatMessage(ChatMessageDto.UserInputRequest request, ChatRoom chatRoom) {

		return ChatMessage.builder()
			.chatRoom(chatRoom)
			.status(SenderStatus.MEMBER)
			.userInput(request.getUserInput())
			.build();
	}
}

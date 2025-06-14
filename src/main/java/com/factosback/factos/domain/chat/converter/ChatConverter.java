package com.factosback.factos.domain.chat.converter;

import com.factosback.factos.domain.chat.dto.ChatMessageDto;
import com.factosback.factos.domain.chat.model.ChatMessage;
import com.factosback.factos.domain.member.model.Member;

public class ChatConverter {

	public static ChatMessage convertToChatMessage(ChatMessageDto.UserInputRequest request, Member member) {
		return ChatMessage.builder()
			.case_summary(request.getCaseSummary())
			.member_evidence(request.getMemberEvidence())
			.opponent_claim(request.getOpponentClaim())
			.build();
	}
}

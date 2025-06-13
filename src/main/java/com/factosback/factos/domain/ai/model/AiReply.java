package com.factosback.factos.domain.ai.model;

import com.factosback.factos.domain.chat.model.ChatMessage;
import com.factosback.factos.global.common.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiReply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	private Integer caseNumber;

	private String contextSummary;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_message_id")
	private ChatMessage chatMessage;

	public void setChatMessage(ChatMessage chatMessage) {
		this.chatMessage = chatMessage;
	}
}

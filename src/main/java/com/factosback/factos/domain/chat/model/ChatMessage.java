package com.factosback.factos.domain.chat.model;


import com.factosback.factos.domain.ai.model.AiReply;
import com.factosback.factos.global.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ChatMessage extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	@Column(length = 15, nullable = false)
	private SenderStatus status = SenderStatus.MEMBER;

	private String userInput;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_room_id")
	private ChatRoom chatRoom;

	@OneToOne(mappedBy = "chatMessage", cascade = CascadeType.ALL, orphanRemoval = true)
	private AiReply aiReply;

	public void addAiReply(AiReply aiReply) {
		this.aiReply = aiReply;
		aiReply.setChatMessage(this); // 양방향 연관관계 설정
	}
}

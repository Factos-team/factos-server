package com.factosback.factos.domain.chat.model;


import com.factosback.factos.domain.ai.model.AiReply;
import com.factosback.factos.global.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	// @Enumerated(EnumType.STRING)
	// @Column(columnDefinition = "VARCHAR(15) DEFAULT 'MEMBER'")
	// private SenderStatus status;

	private String case_summary;

	private String member_evidence;

	private String opponent_claim;

	@OneToOne(mappedBy = "chatMessage", cascade = CascadeType.ALL, orphanRemoval = true)
	private AiReply aiReply;

	public void addAiReply(AiReply aiReply) {
		this.aiReply = aiReply;
		aiReply.setChatMessage(this); // 양방향 연관관계 설정
	}
}

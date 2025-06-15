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

	// 클로드 응답
	private String claudeResponse;

	// 사건 번호 (유저 입력에 관련된 5개 정도의 사건번호 들어옴)
	private String caseNumber;

	// 문맥 기억용 contextSummary
	private String contextSummary;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_message_id")
	private ChatMessage chatMessage;

	public void setChatMessage(ChatMessage chatMessage) {
		this.chatMessage = chatMessage;
	}
}

package com.factosback.factos.domain.chat.model;

import java.util.ArrayList;
import java.util.List;

import com.factosback.factos.domain.legal.model.LegalCase;
import com.factosback.factos.global.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Column(columnDefinition = "VARCHAR(15) DEFAULT 'MEMBER'")
	private SenderStatus status;

	private String content;

	@OneToMany(mappedBy = "chatMessage", cascade = CascadeType.ALL)
	private List<LegalCase> legalCaseList = new ArrayList<>();
}

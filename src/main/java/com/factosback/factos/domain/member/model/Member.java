package com.factosback.factos.domain.member.model;

import java.util.ArrayList;
import java.util.List;

import com.factosback.factos.domain.chat.model.ChatRoom;
import com.factosback.factos.domain.precedent.model.PrecedentSearch;
import com.factosback.factos.domain.term.model.TermTranslation;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "MEMBER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	private String nickname;

	@Column(nullable = false)
	private String profileImageUrl;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
	private MemberStatus status;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<ChatRoom> chatRoomList = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<PrecedentSearch> precedentSearchList = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<TermTranslation> termTranslationList = new ArrayList<>();
}

package com.factosback.factos.domain.term.model;

import java.util.ArrayList;
import java.util.List;

import com.factosback.factos.domain.member.model.Member;
import com.factosback.factos.global.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class TermTranslation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String legalTerm;

	// private String generalTerm;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "termTranslation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GeneralTerm> generalTerms = new ArrayList<>();

	public void addGeneralTerms(List<GeneralTerm> generalTerms) {
		this.generalTerms.addAll(generalTerms);
		generalTerms.forEach(gt -> gt.setTermTranslation(this));
	}
}

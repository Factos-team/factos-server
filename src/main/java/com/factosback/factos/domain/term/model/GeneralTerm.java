package com.factosback.factos.domain.term.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class GeneralTerm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String generalTerm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "term_translation_id")
	private TermTranslation termTranslation;

	public void setTermTranslation(TermTranslation termTranslation) {
		this.termTranslation = termTranslation;
	}
}

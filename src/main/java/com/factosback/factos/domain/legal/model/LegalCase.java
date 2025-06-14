package com.factosback.factos.domain.legal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "legal_cases")
public class LegalCase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String caseName;

	@Column(columnDefinition = "TEXT")
	private String caseNumber;

	@Column(columnDefinition = "TEXT")
	private String caseType;

	@Column(columnDefinition = "TEXT")
	private String issues;

	@Column(columnDefinition = "TEXT")
	private String referenceLaws;

	@Column(columnDefinition = "TEXT")
	private String referencedCases;

	@Column(columnDefinition = "TEXT")
	private String context;

	@Column(columnDefinition = "TEXT")
	private String summary;

	@Transient
	@Column(name = "embedding_summary", columnDefinition = "vector(1024)")
	private float[] embeddingSummary;

	@Transient
	@Column(name = "embedding_context", columnDefinition = "vector(1024)")
	private float[] embeddingContext;
}

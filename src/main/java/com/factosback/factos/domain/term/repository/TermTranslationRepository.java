package com.factosback.factos.domain.term.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.term.model.TermTranslation;

public interface TermTranslationRepository extends JpaRepository<TermTranslation, Long> {
	List<TermTranslation> findByLegalTerm(String legalTerm);
}

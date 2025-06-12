package com.factosback.factos.domain.term.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.term.model.TermTranslation;

public interface TermTranslationRepository extends JpaRepository<TermTranslation, Long> {
	Optional<TermTranslation> findByLegalTerm(String legalTerm);
}

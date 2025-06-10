package com.factosback.factos.domain.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.term.model.TermTranslation;

public interface TermTranslationRepository extends JpaRepository<TermTranslation, Long> {

}

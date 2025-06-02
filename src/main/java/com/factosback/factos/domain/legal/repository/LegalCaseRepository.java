package com.factosback.factos.domain.legal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.legal.model.LegalCase;

public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
}

package com.factosback.factos.domain.term.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.term.model.Term;

public interface TermRepository extends JpaRepository<Term, Long> {

	Optional<Term> findByLegalTerm(String legalTerm);
}

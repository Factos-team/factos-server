package com.factosback.factos.domain.term.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.term.model.Term;

public interface TermRepository extends JpaRepository<Term, Long> {

	// termReply를 즉시 로딩으로 가져옴
	@EntityGraph(attributePaths = {"termReply"})
	Optional<Term> findByLegalTerm(String legalTerm);
}

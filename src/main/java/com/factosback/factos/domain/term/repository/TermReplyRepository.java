package com.factosback.factos.domain.term.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.term.model.Term;
import com.factosback.factos.domain.term.model.TermReply;

public interface TermReplyRepository extends JpaRepository<TermReply, Long> {

	Optional<TermReply> findByTerm(Term term);
}

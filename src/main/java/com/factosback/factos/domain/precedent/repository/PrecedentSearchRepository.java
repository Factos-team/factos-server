package com.factosback.factos.domain.precedent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.precedent.model.PrecedentSearch;

public interface PrecedentSearchRepository extends JpaRepository<PrecedentSearch, Long> {

	Optional<PrecedentSearch> findByCaseNumber(Integer caseNumber);
}

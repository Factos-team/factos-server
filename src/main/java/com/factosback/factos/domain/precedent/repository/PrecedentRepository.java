package com.factosback.factos.domain.precedent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.precedent.error.PrecedentErrorCode;
import com.factosback.factos.domain.precedent.model.Precedent;
import com.factosback.factos.global.error.exception.RestApiException;

public interface PrecedentRepository extends JpaRepository<Precedent, Long> {

	default Precedent findPrecedentById(Long id) {
		return findById(id)
			.orElseThrow(() -> new RestApiException(PrecedentErrorCode.PRECEDENT_NOT_FOUND));
	}

	Optional<Precedent> findByCaseNumber(Integer caseName);
}

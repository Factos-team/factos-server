package com.factosback.factos.domain.precedent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.precedent.converter.PrecedentConverter;
import com.factosback.factos.domain.precedent.dto.GetPrecedentDto;
import com.factosback.factos.domain.precedent.error.PrecedentErrorCode;
import com.factosback.factos.domain.precedent.model.Precedent;
import com.factosback.factos.domain.precedent.repository.PrecedentRepository;
import com.factosback.factos.domain.precedent.repository.PrecedentSearchRepository;
import com.factosback.factos.global.error.exception.RestApiException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PrecedentService {

	private final PrecedentRepository precedentRepository;
	private final PrecedentSearchRepository precedentSearchRepository;

	public GetPrecedentDto.Response getPrecedent(Integer caseNumber) {

		Precedent precedent = precedentRepository.findByCaseNumber(caseNumber)
			.orElseThrow(() -> new RestApiException(PrecedentErrorCode.PRECEDENT_NOT_FOUND));

		return PrecedentConverter.convertToGetPrecedentDto(precedent);
	}
}

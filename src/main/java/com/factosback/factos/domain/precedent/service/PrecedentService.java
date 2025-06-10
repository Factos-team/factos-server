package com.factosback.factos.domain.precedent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.precedent.repository.PrecedentRepository;
import com.factosback.factos.domain.precedent.repository.PrecedentSearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PrecedentService {

	private final PrecedentRepository precedentRepository;
	private final PrecedentSearchRepository precedentSearchRepository;
}

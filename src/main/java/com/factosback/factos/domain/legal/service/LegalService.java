package com.factosback.factos.domain.legal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.legal.repository.LegalCaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LegalService {

	private final LegalCaseRepository legalCaseRepository;
}

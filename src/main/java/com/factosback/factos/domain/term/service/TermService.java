package com.factosback.factos.domain.term.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.term.repository.TermTranslationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TermService {

	private final TermTranslationRepository termTranslationRepository;
}

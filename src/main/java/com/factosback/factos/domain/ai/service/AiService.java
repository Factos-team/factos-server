package com.factosback.factos.domain.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.ai.repository.AiReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AiService {

	private final AiReplyRepository aiReplyRepository;
}

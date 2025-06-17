package com.factosback.factos.domain.term.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.ai.util.AiClient;
import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.domain.term.converter.TermConverter;
import com.factosback.factos.domain.term.dto.GetTermDto;
import com.factosback.factos.domain.term.model.Term;
import com.factosback.factos.domain.term.model.TermReply;
import com.factosback.factos.domain.term.repository.TermReplyRepository;
import com.factosback.factos.domain.term.repository.TermRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TermService {

	private final TermRepository termRepository;
	private final TermReplyRepository termReplyRepository;
	private final AiClient aiClient;

	@Transactional
	public GetTermDto.Response processGetTerm(GetTermDto.Request request) {

		String legalTerm = request.getLegalTerm();

		// 1. DB에서 term + term_reply 조회
		Optional<Term> optionalTerm = termRepository.findByLegalTerm(legalTerm);

		if (optionalTerm.isPresent() && optionalTerm.get().getTermReply() != null) {
			// DB에 이미 응답 있음
			return TermConverter.convertToResponse(optionalTerm.get());
		}

		// 2. AI 서버에 요청
		AiRequestDto.TermExplanation aiRequest = AiRequestDto.TermExplanation.builder()
			.legalTerm(legalTerm)
			.build();

		PrecedentReplyDto.Response aiResponse = aiClient.getTermExplanation(aiRequest);

		// 3. DB에 저장
		Term term = optionalTerm.orElseGet(() -> termRepository.save(
			Term.builder().legalTerm(legalTerm).build()
		));

		TermReply termReply = TermReply.builder()
			.claudeResponse(aiResponse.getClaudeResponse())
			.build();

		// 연관관계 편의 메서드 호출
		term.addTermReply(termReply);

		termReplyRepository.save(termReply);

		// 4. 응답 반환
		return TermConverter.convertToResponse(term);
	}
}

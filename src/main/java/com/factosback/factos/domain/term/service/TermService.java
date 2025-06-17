package com.factosback.factos.domain.term.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factosback.factos.domain.ai.dto.AiRequestDto;
import com.factosback.factos.domain.ai.util.AiClient;
import com.factosback.factos.domain.precedent.dto.PrecedentReplyDto;
import com.factosback.factos.domain.term.converter.TermConverter;
import com.factosback.factos.domain.term.dto.GetTermDto;
import com.factosback.factos.domain.term.dto.TranslateTermDto;
import com.factosback.factos.domain.term.model.GeneralTerm;
import com.factosback.factos.domain.term.model.Term;
import com.factosback.factos.domain.term.model.TermReply;
import com.factosback.factos.domain.term.model.TermTranslation;
import com.factosback.factos.domain.term.repository.TermReplyRepository;
import com.factosback.factos.domain.term.repository.TermRepository;
import com.factosback.factos.domain.term.repository.TermTranslationRepository;
import com.factosback.factos.global.util.OpenApiClient;
import com.factosback.factos.global.config.OpenApiProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TermService {

	// private final TermTranslationRepository termTranslationRepository;
	private final TermRepository termRepository;
	private final TermReplyRepository termReplyRepository;
	private final AiClient aiClient;
	private final OpenApiClient openApiClient;
	private final OpenApiProperties openApiProperties;

	@Transactional
	public GetTermDto.Response processGetTerm(GetTermDto.Request request) {

		String legalTerm = request.getLegalTerm();
		log.info("요청 legalTerm: {}", legalTerm);

		// 1. DB에서 term + term_reply 조회
		Optional<Term> optionalTerm = termRepository.findByLegalTerm(legalTerm);

		if (optionalTerm.isPresent() && optionalTerm.get().getTermReply() != null) {
			// DB에 이미 응답 있음
			log.info("DB에 이미 termReply 존재: {}", optionalTerm.get().getTermReply().getClaudeResponse());
			return TermConverter.convertToResponse(optionalTerm.get());
		}

		// 2. AI 서버에 요청
		AiRequestDto.TermExplanation aiRequest = AiRequestDto.TermExplanation.builder()
			.legalTerm(legalTerm)
			.build();
		log.info("AI 요청 객체: {}", aiRequest);

		PrecedentReplyDto.Response aiResponse = aiClient.getTermExplanation(aiRequest);
		log.info("AI 서버 응답: {}", aiResponse);
		// 3. DB에 저장

		Term term = optionalTerm.orElseGet(() -> termRepository.save(
			Term.builder().legalTerm(legalTerm).build()
		));
		log.info("Term: {}", term);

		TermReply termReply = TermReply.builder()
			.claudeResponse(aiResponse.getClaudeResponse())
			.build();
		log.info("TermReply: {}", termReply);

		// 연관관계 편의 메서드 호출 (핵심 변경 부분)
		term.addTermReply(termReply);

		termReplyRepository.save(termReply);

		// 4. 응답 반환
		return TermConverter.convertToResponse(term);
	}


	// @Transactional
	// public TranslateTermDto.Response processTranslation(TranslateTermDto.UserInputRequest request, Object member) {
	//
	// 	String content = request.getContent();
	// 	String legalTerm = extractLegalTerm(content);
	//
	// 	List<TermTranslation> existingTranslations = termTranslationRepository.findByLegalTerm(legalTerm);
	//
	// 	if (!existingTranslations.isEmpty()) {
	// 		return TermConverter.convertToTranslateTermDto(existingTranslations);
	// 	}
	//
	// 	TermTranslation translation = TermTranslation.builder()
	// 		.content(content)
	// 		.legalTerm(legalTerm)
	// 		.member(null)
	// 		.build();
	//
	// 	// API 요청 준비 및 응답
	// 	TranslateTermDto.OpenApiRequest apiRequest = TranslateTermDto.OpenApiRequest.builder()
	// 		.oc(openApiProperties.oc())
	// 		.target("lstrmRlt")
	// 		.type(openApiProperties.type())
	// 		.query(legalTerm)
	// 		.build();
	//
	// 	List<String> generalTerms = openApiClient.getGeneralTerms(apiRequest);
	//
	// 	// GeneralTerm 객체로 변환 후 저장
	// 	List<GeneralTerm> generalTermEntities = generalTerms.stream()
	// 		.map(gt -> GeneralTerm.builder().generalTerm(gt).build())
	// 		.toList();
	//
	// 	translation.addGeneralTerms(generalTermEntities);
	// 	termTranslationRepository.save(translation);
	//
	// 	return TermConverter.convertToTranslateTermDto(translation);
	// }
	//
	// // 임시 extractLegalTerm 구현
	// // TODO: 실제 추출 로직 구현
	// private String extractLegalTerm(String content) {
	// 	return "청원";
	// }
}

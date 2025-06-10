package com.factosback.factos.domain.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.ai.model.AiReply;

public interface AiReplyRepository extends JpaRepository<AiReply, Long> {
}

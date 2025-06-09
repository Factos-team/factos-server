package com.factosback.factos.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.chat.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}

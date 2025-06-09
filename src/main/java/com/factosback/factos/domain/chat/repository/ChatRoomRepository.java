package com.factosback.factos.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.chat.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}

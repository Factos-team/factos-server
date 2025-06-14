package com.factosback.factos.domain.chat.error;

import org.springframework.http.HttpStatus;

import com.factosback.factos.global.error.code.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatErrorCode implements ErrorCode {

	CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 채팅방을 찾을 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}

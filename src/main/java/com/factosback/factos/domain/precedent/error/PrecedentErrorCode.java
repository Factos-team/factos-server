package com.factosback.factos.domain.precedent.error;

import org.springframework.http.HttpStatus;

import com.factosback.factos.global.error.code.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrecedentErrorCode implements ErrorCode {

	PRECEDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 PRECEDENT를 찾을 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;

}

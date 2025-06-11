package com.factosback.factos.global.error.exception;

import com.factosback.factos.global.error.code.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

	private final ErrorCode errorCode;
}

package com.factosback.factos.global.error.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터가 올바르지 않습니다."),
	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 리소스입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않는 HTTP Method입니다."),
	ENDPOINT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 API 경로입니다.");

	private final HttpStatus httpStatus;

	private final String message;
}

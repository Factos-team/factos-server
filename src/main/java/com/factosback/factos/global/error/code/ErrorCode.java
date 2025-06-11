package com.factosback.factos.global.error.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

	String name();

	// 어떤 HTTP 상태코드인지
	HttpStatus getHttpStatus();

	// 사용자에게 보여줄 메시지
	String getMessage();
}

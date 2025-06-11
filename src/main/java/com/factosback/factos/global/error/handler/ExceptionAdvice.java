package com.factosback.factos.global.error.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.factosback.factos.global.error.code.CommonErrorCode;
import com.factosback.factos.global.error.code.ErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<Object> handleRestApiException(RestApiException e) {
		ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
		log.warn("handlerIllegalArgument 입니다.");
		ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleExceptionInternal(errorCode, e.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.warn("handleHttpRequestMethodNotSupportedException 입니다.");
		ErrorCode errorCode = CommonErrorCode.METHOD_NOT_ALLOWED;
		return handleExceptionInternal(errorCode);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.warn("handleMethodArgumentNotValid 입니다.");
		ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleExceptionInternal(errorCode, e.getMessage());
	}

	private static String getDefaultMessage(MethodArgumentNotValidException e) {
		return e.getBindingResult()
			.getAllErrors()
			.stream()
			.map(ObjectError::getDefaultMessage)
			.collect(Collectors.joining("\u00a0"));
	}

	private ResponseEntity<Object> handleExceptionInternal (final ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus()).body(errorCode);
	}

	private ResponseEntity<Object> handleExceptionInternal (final ErrorCode errorCode, final String message) {
		return ResponseEntity.status(errorCode.getHttpStatus()).body(message);
	}
}

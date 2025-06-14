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
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.factosback.factos.global.error.code.CommonErrorCode;
import com.factosback.factos.global.error.code.ErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;
import com.factosback.factos.global.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<Object> handleRestApiException(RestApiException e) {
		ErrorCode errorCode = e.getErrorCode();
		return handleExceptionInternal(errorCode);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
		log.warn("handlerIllegalArgument 발생");
		ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleExceptionInternal(errorCode, e.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.warn("handleHttpRequestMethodNotSupportedException 발생");
		ErrorCode errorCode = CommonErrorCode.METHOD_NOT_ALLOWED;
		return handleExceptionInternal(errorCode);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.warn("handleMethodArgumentNotValidException 발생");
		ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleExceptionInternal(errorCode, e.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.warn("handleNoHandlerFoundException 발생");
		ErrorCode errorCode = CommonErrorCode.ENDPOINT_NOT_FOUND;
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
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ApiResponse.createFail(errorCode));
	}

	private ResponseEntity<Object> handleExceptionInternal (final ErrorCode errorCode, final String message) {
		return ResponseEntity.status(errorCode.getHttpStatus()).body(ApiResponse.createFail(message));
	}
}

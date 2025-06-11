package com.factosback.factos.global.response;

import com.factosback.factos.global.error.code.ErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiResponse<T> {

	private static final boolean SUCCESS_STATUS = true;

	private static final boolean FAIL_STATUS = false;

	private static final String SUCCESS_MESSAGE = "API 호출 성공";

	private Boolean isSuccess;

	private String message;

	private T data;

	public static <T> ApiResponse<T> createSuccess(T data) {
		return new ApiResponse<T>(SUCCESS_STATUS, SUCCESS_MESSAGE, data);
	}

	public static <T> ApiResponse<T> createSuccessWithNoContent() {
		return new ApiResponse<T>(SUCCESS_STATUS, SUCCESS_MESSAGE, null);
	}

	public static <T> ApiResponse<T> createFail(ErrorCode errorCode) {
		return new ApiResponse<T>(FAIL_STATUS, errorCode.getMessage(), null);
	}

	public static <T> ApiResponse<T> createFail(String message) {
		return new ApiResponse<T>(FAIL_STATUS, message, null);
	}
}

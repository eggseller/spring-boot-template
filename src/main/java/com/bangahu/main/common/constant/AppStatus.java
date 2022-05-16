package com.bangahu.main.common.constant;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppStatus {
	OK(HttpStatus.OK.value(), "요청이 정상 처리되었습니다."),
	ACCEPTED(HttpStatus.ACCEPTED.value(), "요청을 처리 중입니다. 잠시만 기다려 주세요."),
	FAILD(HttpStatus.SERVICE_UNAVAILABLE.value(), "요청 처리에 실패하였습니다. 잠시 후 다시 시도해 보세요."),
	NOT_FOUND(HttpStatus.NOT_FOUND.value(), "요청한 데이타를 찾을 수 없습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "사용자 인증이 되지 않았습니다. 로그인해주세요."),
	FORBIDDEN(HttpStatus.FORBIDDEN.value(), "요청 권한이 없습니다."),
	EXIST_USER(HttpStatus.NOT_ACCEPTABLE.value(), "이미 동일한 사용자가 있습니다.");
	
	private final int status;
	private final String message;
	
	public int status() {
		return this.status;
	}
	
	public String message() {
		return this.message;
	}
}

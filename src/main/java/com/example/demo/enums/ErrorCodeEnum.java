package com.example.demo.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ErrorCodeEnum {

	GENERIC_EXCEPTION("10001", "Unexpected error in Employee Management System service..");

	String errorCode;
	String errorMessage;

	ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}

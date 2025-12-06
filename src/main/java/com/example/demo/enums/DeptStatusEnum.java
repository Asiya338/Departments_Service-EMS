package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeptStatusEnum {

	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String status;
}

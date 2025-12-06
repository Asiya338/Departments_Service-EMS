package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeptUpdateDTO {

	@Size(min = 3, max = 30, message = "Department name must be between 3 and 30 characters")
	private String name;

	@Size(min = 3, max = 3, message = "Department code must be 3 characters")
	private String code;

	private Long departmentHeadId;
}

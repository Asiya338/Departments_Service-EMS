package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentUpdateDTO {

	@Size(min = 3, max = 30, message = "Department name must be between 3 and 30 characters")
	private String name;

	@Size(min = 5, max = 5, message = "Department code must be 5 characters")
	private String code;

	private Long departmentHeadId;
}

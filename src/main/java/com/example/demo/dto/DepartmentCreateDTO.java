package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCreateDTO {

	@NotBlank(message = "Department name is mandatory")
	@Size(min = 3, max = 30, message = "Department name must be between 3 and 30 characters")
	private String name;

	@NotBlank(message = "Department code is mandatory")
	@Size(min = 5, max = 5, message = "Department code must be 5 characters")
	private String code;

	@NotNull(message = "Department Head ID is mandatory")
	private Long departmentHeadId;

	public boolean existsByDepartmentHeadId(Long departmentHeadId2) {
		// TODO Auto-generated method stub
		return false;
	}
}

package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentCreateDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.dto.DepartmentUpdateDTO;

public interface DepartmentService {

	DepartmentResponseDTO createDepartment(DepartmentCreateDTO createDTO);

	List<DepartmentResponseDTO> getAllDepartments();

	DepartmentResponseDTO getDepartmentById(int deptId);

	DepartmentResponseDTO deleteDepartmentById(int deptId);

	DepartmentResponseDTO validateDepartmentById(int deptId);

	DepartmentResponseDTO updateDepartmentById(int deptId, DepartmentUpdateDTO updateDTO);

}

package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DeptCreateDTO;
import com.example.demo.dto.DeptResponseDTO;
import com.example.demo.dto.DeptUpdateDTO;

import jakarta.validation.Valid;

public interface DepartmentService {

	DeptResponseDTO createDepartment(@Valid DeptCreateDTO createDTO);

	List<DeptResponseDTO> getAllDepartments();

	DeptResponseDTO getDepartmentById(int deptId);

	DeptResponseDTO deleteDepartmentById(int deptId);

	DeptResponseDTO validateDepartmentById(int deptId);

	DeptResponseDTO updateDepartmentById(int deptId, @Valid DeptUpdateDTO updateDTO);

}

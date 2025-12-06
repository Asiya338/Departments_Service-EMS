package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DeptCreateDTO;
import com.example.demo.dto.DeptResponseDTO;
import com.example.demo.dto.DeptUpdateDTO;

public interface DepartmentService {

	DeptResponseDTO createDepartment(DeptCreateDTO createDTO);

	List<DeptResponseDTO> getAllDepartments();

	DeptResponseDTO getDepartmentById(int deptId);

	DeptResponseDTO deleteDepartmentById(int deptId);

	DeptResponseDTO validateDepartmentById(int deptId);

	DeptResponseDTO updateDepartmentById(int deptId, DeptUpdateDTO updateDTO);

}

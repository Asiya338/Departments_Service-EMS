package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DeptCreateDTO;
import com.example.demo.dto.DeptResponseDTO;
import com.example.demo.dto.DeptUpdateDTO;
import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepo;
import com.example.demo.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepo departmentRepo;
	private final ModelMapper modelMapper;

	@Override
	public DeptResponseDTO createDepartment(DeptCreateDTO createDTO) {
		log.info("Create department DTO : {} ", createDTO);

		Department department = modelMapper.map(createDTO, Department.class);

		Department savedDepartment = departmentRepo.save(department);

		DeptResponseDTO response = modelMapper.map(savedDepartment, DeptResponseDTO.class);

		log.info("Department created : {} ", response);

		return response;
	}

	@Override
	public List<DeptResponseDTO> getAllDepartments() {
		log.info("Fetching all departments");

		List<Department> allDepartments = departmentRepo.findAll();

		List<DeptResponseDTO> response = allDepartments.stream()
				.map(department -> modelMapper.map(department, DeptResponseDTO.class)).toList();

		log.info("All departments : {} ", response);

		return response;
	}

	@Override
	public DeptResponseDTO getDepartmentById(int deptId) {
		log.info(" department id || getDepartmentById : {} ", deptId);

		Department department = departmentRepo.findById((long) deptId).orElse(null);
		DeptResponseDTO response = new DeptResponseDTO();

		if (department != null) {
			response = modelMapper.map(department, DeptResponseDTO.class);
		}

		log.info("Department with ID || getDepartmentById : {} ", response);

		return response;
	}

	@Override
	public DeptResponseDTO deleteDepartmentById(int deptId) {
		log.info("Deleting department with ID : {}  ", deptId);

		departmentRepo.deleteById((long) deptId);

		DeptResponseDTO response = new DeptResponseDTO();
		response.setId(deptId);
		response.setDeletedBy(LocalDateTime.now());

		log.info("Department deleted : {} ", response);

		return response;
	}

	@Override
	public DeptResponseDTO validateDepartmentById(int deptId) {
		log.info(" department id || validateDepartmentById : {} ", deptId);

		Department department = departmentRepo.findById((long) deptId).orElse(null);
		DeptResponseDTO response = new DeptResponseDTO();

		if (department != null) {
			response = modelMapper.map(department, DeptResponseDTO.class);
		}

		log.info("Department validated || validateDepartmentById : {} ", response);

		return response;
	}

	@Override
	public DeptResponseDTO updateDepartmentById(int deptId, DeptUpdateDTO updateDTO) {
		log.info("Update department DTO : {} ", updateDTO);

		Department department = modelMapper.map(updateDTO, Department.class);

		Department savedDepartment = departmentRepo.save(department);

		DeptResponseDTO response = modelMapper.map(savedDepartment, DeptResponseDTO.class);

		log.info("Department updated : {} ", response);

		return response;
	}

}

package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.dto.DepartmentCreateDTO;
import com.example.demo.dto.DepartmentUpdateDTO;
import com.example.demo.entity.Department;
import com.example.demo.enums.ErrorCodeEnum;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.DepartmentRepo;
import com.example.demo.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepo departmentRepo;
	private final ModelMapper modelMapper;

	@Override
	public DepartmentResponseDTO createDepartment(DepartmentCreateDTO createDTO) {
		log.info("Create department DTO : {} ", createDTO);

		// to check whether department with same code already exists
		if (departmentRepo.existsByCode(createDTO.getCode())) {
			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DEPT_CODE.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DEPT_CODE.getErrorMessage());
		}

		// to check department head id is unique
		if (departmentRepo.existsByDepartmentHeadId(createDTO.getDepartmentHeadId())) {
			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DEPT_HEAD_ID.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DEPT_HEAD_ID.getErrorMessage());
		}

		Department department = new Department();
		department.setName(createDTO.getName());
		department.setCode(createDTO.getCode().toUpperCase());
		department.setDepartmentHeadId(createDTO.getDepartmentHeadId());

		Department savedDepartment = departmentRepo.save(department);

		DepartmentResponseDTO response = modelMapper.map(savedDepartment, DepartmentResponseDTO.class);

		log.info("Department created : {} ", response);

		return response;
	}

	@Override
	public List<DepartmentResponseDTO> getAllDepartments() {
		log.info("Fetching all departments");

		List<Department> allDepartments = departmentRepo.findAll();

		List<DepartmentResponseDTO> response = allDepartments.stream()
				.map(department -> modelMapper.map(department, DepartmentResponseDTO.class)).toList();

		log.info("All departments : {} ", response);

		return response;
	}

	@Override
	public DepartmentResponseDTO getDepartmentById(int deptId) {
		log.info(" department id || getDepartmentById : {} ", deptId);

		if (!departmentRepo.existsById(deptId)) {
			throw new ResourceNotFoundException(ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorCode(),
					ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorMessage() + " : " + deptId);
		}

		Department department = departmentRepo.findById((long) deptId).orElse(null);

		DepartmentResponseDTO response = modelMapper.map(department, DepartmentResponseDTO.class);

		log.info("Department with ID : {} || getDepartmentById : {} ", deptId, response);

		return response;
	}

	@Override
	public DepartmentResponseDTO deleteDepartmentById(int deptId) {
		log.info("Deleting department with ID : {}  ", deptId);

		if (!departmentRepo.existsById(deptId)) {
			throw new ResourceNotFoundException(ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorCode(),
					ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorMessage() + " : " + deptId
							+ " || Failed to delete");
		}

		departmentRepo.deleteById((long) deptId);

		DepartmentResponseDTO response = new DepartmentResponseDTO();
		response.setId(deptId);
		response.setDeletedAt(LocalDateTime.now());

		log.info("Department deleted : {} ", response);

		return response;
	}

	@Override
	public DepartmentResponseDTO validateDepartmentById(int deptId) {
		log.info(" department id || validateDepartmentById : {} ", deptId);

		DepartmentResponseDTO response = new DepartmentResponseDTO();
		if (!departmentRepo.existsById(deptId)) {
			log.info("There is no department with ID : {}", deptId);
			response.setValid(false);
			return response;
		}

		Department department = departmentRepo.findById((long) deptId).orElse(null);

		response = modelMapper.map(department, DepartmentResponseDTO.class);
		response.setValid(true);

		log.info("Department validated || validateDepartmentById : {} ", response);

		return response;
	}

	@CacheEvict(value = "departmentCache", key = "#deptId")
	@Override
	public DepartmentResponseDTO updateDepartmentById(int deptId, DepartmentUpdateDTO updateDTO) {
		log.info("Update department DTO : {} ", updateDTO);

		Department department = departmentRepo.findById((long) deptId).orElseThrow(() -> new ResourceNotFoundException(
				ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorCode(),
				ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorMessage() + " : " + deptId + " || Updation failed"));

		// to check whether department with same code already exists
		if (departmentRepo.existsByCode(updateDTO.getCode()) && !updateDTO.getCode().equals(department.getCode())) {
			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DEPT_CODE.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DEPT_CODE.getErrorMessage());
		}

		// to check department head id is unique
		if (departmentRepo.existsByDepartmentHeadId(updateDTO.getDepartmentHeadId())
				&& !updateDTO.getDepartmentHeadId().equals(department.getDepartmentHeadId())) {
			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DEPT_HEAD_ID.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DEPT_HEAD_ID.getErrorMessage());
		}

		modelMapper.map(updateDTO, department);

		Department savedDepartment = departmentRepo.save(department);

		DepartmentResponseDTO response = modelMapper.map(savedDepartment, DepartmentResponseDTO.class);

		log.info("Department updated : {} ", response);

		return response;
	}

}

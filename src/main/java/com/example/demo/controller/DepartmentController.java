package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeptCreateDTO;
import com.example.demo.dto.DeptResponseDTO;
import com.example.demo.dto.DeptUpdateDTO;
import com.example.demo.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	@PostMapping()
	public ResponseEntity<DeptResponseDTO> createDepartment(@Valid @RequestBody DeptCreateDTO createDTO) {
		log.info("Creating department with details : {} ", createDTO);

		DeptResponseDTO response = departmentService.createDepartment(createDTO);

		URI location = URI.create("/api/v1/departments/" + response.getId());

		log.info("Department created : {} ", response);

		return ResponseEntity.created(location).body(response);
	}

	@GetMapping()
	public ResponseEntity<List<DeptResponseDTO>> getAllDepartments() {
		log.info("Get all the departments");

		List<DeptResponseDTO> response = departmentService.getAllDepartments();

		log.info("All departments : {} ", response);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{deptId}")
	public ResponseEntity<DeptResponseDTO> getDepartmentById(@PathVariable int deptId) {
		log.info("Fetch department by deptId : {} ", deptId);

		DeptResponseDTO response = departmentService.getDepartmentById(deptId);

		log.info("Department by ID: {} || Department : {} ", deptId, response);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{deptId}")
	public ResponseEntity<DeptResponseDTO> deleteDepartmentById(@PathVariable int deptId) {
		log.info("Delete department by deptId : {} ", deptId);

		DeptResponseDTO response = departmentService.deleteDepartmentById(deptId);

		log.info("Department deleted with ID : {} || deleted department : {} ", deptId, response);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{deptId}")
	public ResponseEntity<DeptResponseDTO> updateDepartmentById(@PathVariable int deptId,
			@Valid @RequestBody DeptUpdateDTO updateDTO) {
		log.info("Update department by deptId : {} || UPDATE details : {}  ", deptId, updateDTO);

		DeptResponseDTO response = departmentService.updateDepartmentById(deptId, updateDTO);

		log.info("Department updated with ID : {} || updated Department : {}", deptId, updateDTO);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/check/{deptId}")
	public ResponseEntity<DeptResponseDTO> validateDepartmentById(@PathVariable int deptId) {
		log.info("Validate department exists by deptId : {} ", deptId);

		DeptResponseDTO response = departmentService.validateDepartmentById(deptId);

		log.info("Department validated with ID : {} || department : {}", deptId, response);

		return ResponseEntity.ok(response);
	}
}

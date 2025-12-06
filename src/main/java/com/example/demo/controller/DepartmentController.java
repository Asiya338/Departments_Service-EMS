package com.example.demo.controller;

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

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	@PostMapping()
	public ResponseEntity<DeptResponseDTO> createDepartment(@Valid @RequestBody DeptCreateDTO createDTO) {
		log.info("Creating department with details : {} ", createDTO);

		return null;
	}

	@GetMapping()
	public ResponseEntity<List<DeptResponseDTO>> getAllDepartments() {
		log.info("Get all the departments");

		return null;
	}

	@GetMapping("/{deptId}")
	public ResponseEntity<DeptResponseDTO> getDepartmentById(@PathVariable int deptId) {
		log.info("Fetch department by deptId : {} ", deptId);

		return null;
	}

	@DeleteMapping("/{deptId}")
	public ResponseEntity<DeptResponseDTO> deleteDepartmentById(@PathVariable int deptId) {
		log.info("Delete department by deptId : {} ", deptId);

		return null;
	}

	@PutMapping("/{deptId}")
	public ResponseEntity<DeptResponseDTO> updateDepartmentById(@PathVariable int deptId) {
		log.info("Update department by deptId : {} ", deptId);

		return null;
	}

	@GetMapping("/check/{deptId}")
	public ResponseEntity<DeptResponseDTO> validateDepartmentById(@PathVariable int deptId) {
		log.info("Validate department exists by deptId : {} ", deptId);

		return null;
	}
}

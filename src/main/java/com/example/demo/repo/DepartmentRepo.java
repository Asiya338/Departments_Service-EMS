package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	boolean existsByCode(String code);

	boolean existsByDepartmentHeadId(Long departmentHeadId);

	boolean existsById(int deptId);

}

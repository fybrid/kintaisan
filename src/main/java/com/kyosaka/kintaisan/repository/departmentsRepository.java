package com.kyosaka.kintaisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyosaka.kintaisan.entity.departments;

public interface departmentsRepository extends JpaRepository<departments, Integer>{
  boolean existsByDepartmentId(Integer departmentId);
}

package com.kyosaka.kintaisan.repository;

import com.kyosaka.kintaisan.entity.departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface departmentsRepository
        extends JpaRepository<departments, Integer> {

    Optional<departments> findByDepartmentId(int departmentId);
}
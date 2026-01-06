package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kyosaka.kintaisan.entity.departments;
import com.kyosaka.kintaisan.repository.departmentsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class departmentInitializer implements CommandLineRunner{

  private final departmentsRepository departmentsRepository;

  @Override
  public void run (String... args) {
    if (departmentsRepository.existsByDepartmentId(1)){
      return;
    }

    departments department = new departments();
    department.setDepartmentId(1);
    department.setDepartmentName("開発部");

    departmentsRepository.save(department);
  }

}

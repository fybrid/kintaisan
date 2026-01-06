package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.kyosaka.kintaisan.entity.departments;
import com.kyosaka.kintaisan.repository.departmentsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class departmentInitializer implements CommandLineRunner{

  private final departmentsRepository departmentsRepository;

  @Override
  public void run (String... args) {
    if (departmentsRepository.existsByDepartmentId(1)
        && departmentsRepository.existsByDepartmentId(2)
        && departmentsRepository.existsByDepartmentId(3)) {
      return;
    }

    List<departments> departmentsList = new ArrayList<>();

    if (!departmentsRepository.existsByDepartmentId(1)) {
      departments department = new departments();
      department.setDepartmentId(1);
      department.setDepartmentName("開発部");
      departmentsList.add(department);
    }

    if (!departmentsRepository.existsByDepartmentId(2)) {
      departments department = new departments();
      department.setDepartmentId(2);
      department.setDepartmentName("営業部");
      departmentsList.add(department);
    }

    if (!departmentsRepository.existsByDepartmentId(3)) {
      departments department = new departments();
      department.setDepartmentId(3);
      department.setDepartmentName("人事部");
      departmentsList.add(department);
    }

    if (!departmentsList.isEmpty()) {
      departmentsRepository.saveAll(departmentsList);
    }
  }

}

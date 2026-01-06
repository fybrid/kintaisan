package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kyosaka.kintaisan.entity.workplaces;
import com.kyosaka.kintaisan.repository.workplacesRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class workplaceInitializer implements CommandLineRunner {

  private final workplacesRepository workplacesRepository;

  @Override
  public void run (String... args) {
    if (workplacesRepository.existsByWorkplaceId(1)){
      return;
    }

    workplaces workplace = new workplaces();
    workplace.setWorkplaceId(1);
    workplace.setWorkplaceName("本社");

    workplacesRepository.save(workplace);
  }
}

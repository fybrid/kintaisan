package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.kyosaka.kintaisan.entity.workplaces;
import com.kyosaka.kintaisan.repository.workplacesRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class workplaceInitializer implements CommandLineRunner {

  private final workplacesRepository workplacesRepository;

  // TODO: SQLもしくはFlywayで直接データを入れる
  @Override
  public void run (String... args) {
    if (workplacesRepository.existsByWorkplaceId(1)
        && workplacesRepository.existsByWorkplaceId(2)
        && workplacesRepository.existsByWorkplaceId(3)) {
      return;
    }

    List<workplaces> workplacesList = new ArrayList<>();

    if (!workplacesRepository.existsByWorkplaceId(1)) {
      workplaces workplace = new workplaces();
      workplace.setWorkplaceId(1);
      workplace.setWorkplaceName("本社");
      workplacesList.add(workplace);
    }

    if (!workplacesRepository.existsByWorkplaceId(2)) {
      workplaces workplace = new workplaces();
      workplace.setWorkplaceId(2);
      workplace.setWorkplaceName("自宅");
      workplacesList.add(workplace);
    }

    if (!workplacesRepository.existsByWorkplaceId(3)) {
      workplaces workplace = new workplaces();
      workplace.setWorkplaceId(3);
      workplace.setWorkplaceName("支社");
      workplacesList.add(workplace);
    }

    if (!workplacesList.isEmpty()) {
      workplacesRepository.saveAll(workplacesList);
    }
  }
}

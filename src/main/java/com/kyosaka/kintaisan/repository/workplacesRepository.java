package com.kyosaka.kintaisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyosaka.kintaisan.entity.workplaces;

public interface workplacesRepository extends JpaRepository<workplaces, Integer>{

  boolean existsByWorkplaceId(Integer workplaceId);

}

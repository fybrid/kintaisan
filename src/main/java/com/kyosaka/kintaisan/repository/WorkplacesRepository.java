package com.kyosaka.kintaisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyosaka.kintaisan.entity.workplaces;

@Repository
public interface WorkplacesRepository extends JpaRepository<workplaces, Integer> {
    
}

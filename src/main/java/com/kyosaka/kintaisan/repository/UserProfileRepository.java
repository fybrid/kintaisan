package com.kyosaka.kintaisan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyosaka.kintaisan.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

  boolean existsByUserId(String userId);
  Optional<UserProfile> findByUserId(String userId);
}

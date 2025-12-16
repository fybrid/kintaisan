package com.kyosaka.kintaisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyosaka.kintaisan.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
  boolean existsByUserId(String userId);
}

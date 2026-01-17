package com.kyosaka.kintaisan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import com.kyosaka.kintaisan.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
  boolean existsByUserId(String userId);

  Optional<UserAccount> findByUserId(String userId);

  Optional<UserAccount> findByUserIdOrName(String userId, String name);

  List<UserAccount> findByIsActiveTrue(Sort sort);

  //特定の権限（RoleID）を除外する
  List<UserAccount> findByIsActiveTrueAndRoleIdNot(Sort sort, short roleId);
}

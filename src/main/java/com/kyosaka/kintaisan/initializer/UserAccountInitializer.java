package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class UserAccountInitializer implements CommandLineRunner{

  private final UserAccountRepository userAccountRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run (String... args) {
    if (!userAccountRepository.existsByUserId("superadmin")) {
      UserAccount user = new UserAccount();
      user.setUserId("superadmin");
      user.setPassword(passwordEncoder.encode("password"));
      user.setName("SuperAdmin");
      user.setRoleId((short)3);
      user.setIsActive(true);

      userAccountRepository.save(user);
    }

    if (!userAccountRepository.existsByUserId("admin")) {
      UserAccount user = new UserAccount();
      user.setUserId("admin");
      user.setPassword(passwordEncoder.encode("password"));
      user.setName("Admin");
      user.setRoleId((short)2);
      user.setIsActive(true);

      userAccountRepository.save(user);
    }

    if (!userAccountRepository.existsByUserId("user1")) {
      UserAccount user = new UserAccount();
      user.setUserId("user1");
      user.setPassword(passwordEncoder.encode("password"));
      user.setName("User1");
      user.setRoleId((short)1);
      user.setIsActive(true);

      userAccountRepository.save(user);
    }

    if (!userAccountRepository.existsByUserId("deleteduser")) {
      UserAccount user = new UserAccount();
      user.setUserId("deleteduser");
      user.setPassword(passwordEncoder.encode("password"));
      user.setName("Deleteduser");
      user.setRoleId((short)1);
      user.setIsActive(false);

      userAccountRepository.save(user);
    }
  }
}

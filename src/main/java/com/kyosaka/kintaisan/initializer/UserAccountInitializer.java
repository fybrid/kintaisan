package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAccountInitializer implements CommandLineRunner{

  private final UserAccountRepository userAccountRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run (String... args) {
    if (userAccountRepository.existsByUserId("superadmin")) {
      return;
    }

    UserAccount user = new UserAccount();
    user.setUserId("superadmin");
    user.setPassword(passwordEncoder.encode("password"));
    user.setName("SuperAdmin");
    user.setRoleId((short)3);
    user.setIsActive(true);

    userAccountRepository.save(user);
  }
}

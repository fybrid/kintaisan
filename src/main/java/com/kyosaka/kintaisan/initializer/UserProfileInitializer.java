package com.kyosaka.kintaisan.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Component
@Order(2)
@RequiredArgsConstructor
public class UserProfileInitializer implements CommandLineRunner {

  private final UserProfileRepository userProfileRepository;

  @Override
  public void run (String... args) {
    if (userProfileRepository.existsByUserId("superadmin")){
      return;
    }

    UserProfile user = new UserProfile();
    user.setUserId("superadmin");
    user.setDepartmentId(1);
    user.setEmail("admin@admin.com");
    user.setWorkplaceId(1);
    user.setPhoneNumber(234567);

    userProfileRepository.save(user);
  }
}

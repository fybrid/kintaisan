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
    if (!userProfileRepository.existsByUserId("superadmin")){
      UserProfile user = new UserProfile();
      user.setUserId("superadmin");
      user.setDepartmentId(1);
      user.setEmail("superadmin@example.com");
      user.setWorkplaceId(1);
      user.setPhoneNumber("09012345678");

      userProfileRepository.save(user);
    }

    if (!userProfileRepository.existsByUserId("admin")){
      UserProfile user = new UserProfile();
      user.setUserId("admin");
      user.setDepartmentId(2);
      user.setEmail("admin@example.com");
      user.setWorkplaceId(1);
      user.setPhoneNumber("09023456789");

      userProfileRepository.save(user);
    }

    if (!userProfileRepository.existsByUserId("user1")){
      UserProfile user = new UserProfile();
      user.setUserId("user1");
      user.setDepartmentId(3);
      user.setEmail("user1@example.com");
      user.setWorkplaceId(2);
      user.setPhoneNumber("09034567890");

      userProfileRepository.save(user);
    }
  }
}

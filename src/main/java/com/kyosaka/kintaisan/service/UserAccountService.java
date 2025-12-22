package com.kyosaka.kintaisan.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.repository.UserAccountRepository;

@Service
public class UserAccountService {

  private final PasswordEncoder passwordEncoder;
  private final UserAccountRepository userAccountRepository;

  public UserAccountService(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userAccountRepository = userAccountRepository;
  }

  public enum SigninStatus {
    SUCCESS,
    BAD_REQUEST,
    USER_NOT_FOUND,
    PASSWORD_MISMATCH
  }

  public record SigninResult(SigninStatus status, String userId, Short roleId) {}

  public SigninResult signin(String username, String password) {
    if (username == null || password == null || username.isBlank() || password.isBlank()) {
      return new SigninResult(SigninStatus.BAD_REQUEST, null, null);
    }

    Optional<UserAccount> userOpt = userAccountRepository.findByUserIdOrName(username, username);
    if (userOpt.isEmpty()) {
      return new SigninResult(SigninStatus.USER_NOT_FOUND, null, null);
    }

    UserAccount user = userOpt.get();
    if (passwordEncoder.matches(password, user.getPassword())) {
      return new SigninResult(SigninStatus.SUCCESS, user.getUserId(), user.getRoleId());
    }

    return new SigninResult(SigninStatus.PASSWORD_MISMATCH, null, null);
  }
}

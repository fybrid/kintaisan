package com.kyosaka.kintaisan.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.repository.UserAccountRepository;

@RestController
@RequestMapping("/api")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  private final PasswordEncoder passwordEncoder;
  private final UserAccountRepository userAccountRepository;

  public AuthController(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userAccountRepository = userAccountRepository;
  }

  @PostMapping("/signin")
  public ResponseEntity<String> signin(@RequestParam String username, @RequestParam String password) {

    // 入力された値が空の場合
    if (username.isBlank() || password.isBlank()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
    }

    Optional<UserAccount> userOpt = userAccountRepository.findByUserIdOrName(username, username);
    if (userOpt.isEmpty()) {
      logger.info("ログイン失敗: ユーザーが見つかりません username={}", username);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error");
    }

    UserAccount user = userOpt.get();
    if (passwordEncoder.matches(password, user.getPassword())) {
      logger.info("ログイン成功 username={}", username);
      return ResponseEntity.ok("success");
    }

    logger.info("ログイン失敗: パスワード不一致 username={}", username);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error");
  }


}

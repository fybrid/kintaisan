package com.kyosaka.kintaisan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  private final PasswordEncoder passwordEncoder;

  public AuthController(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  // TODO: DBから値を取ってきて代入する (ユーザーのID/Nameがはいる変数/ハッシュ化済パスワード)
  private static String userid = "osaka";

  private static String pw = "password";

  @PostMapping("/signin")
  public String signin(@RequestParam String username, @RequestParam String password) {

    // 入力された値が空の場合
    if (username.isBlank() || password.isBlank()) {
      return "error";
    }

    // TODO: 削除予定
    String hashedPasswordDb = passwordEncoder.encode(pw);

    String hashedPassword = passwordEncoder.encode(password);

    // TODO: 削除予定
    logger.info("password{}",hashedPasswordDb);

    // TODO: 削除予定
    logger.info("password{}",hashedPassword);




    // // 入力された値とDBレコードの値を照合
    if (userid.equals(username)) {
      // TODO: 削除予定 確認用log
      logger.info("usernameが一致しました。 username={}", username);
    }else{
      // TODO: 削除予定 確認用log
      logger.info("usernameが一致していません。 username={}", username);
    }

    // // 入力された値とDBレコードの値を照合
    if (passwordEncoder.matches(password, hashedPasswordDb)) {
      // TODO: 削除予定 確認用log
      logger.info("passwordが一致しました。");
    }else{
      // TODO: 削除予定 確認用log
      logger.info("passwordが一致していません。");
    }

    // TODO: 削除予定 確認用log
    logger.info("signin()メソッドが呼び出されました。");
    return "success";
  }


}

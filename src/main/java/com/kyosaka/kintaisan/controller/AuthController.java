package com.kyosaka.kintaisan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  // TODO: DBから値を取ってきて代入する (ユーザーのID/Nameがはいる変数)
  private static String userid = "osaka";

  @PostMapping("/signin")
  public String signin(@RequestParam String username, @RequestParam String password) {

    // 入力された値が空の場合 (テスト中はコメントアウト)
    // if (username.isBlank() || password.isBlank()) {
    //   return "error";
    // }

    // 入力された値とDBレコードの値を照合
    if (userid.equals(username)) {
      // TODO: 削除予定 確認用log
      logger.info("usernameが一致しました。 username={}", username);
    }else{
      // TODO: 削除予定 確認用log
      logger.info("usernameが一致していません。 username={}", username);
    }

    // TODO: 削除予定 確認用log
    logger.info("signin(メソッドが呼び出されました。");
    return "success";
  }


}

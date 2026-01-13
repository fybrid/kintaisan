package com.kyosaka.kintaisan.controller;

import com.kyosaka.kintaisan.dto.UserAccountCreateRequest;
import com.kyosaka.kintaisan.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin")
public class AdminCreateController {

    private final UserAccountService userAccountService;

    AdminCreateController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

  @PostMapping("/users/create")
  public String createUser(@ModelAttribute UserAccountCreateRequest form) {
    if (Boolean.TRUE.equals(userAccountService.createUser(form))) {
      // 管理者ページindex（ユーザー表）にリダイレクト
      return "redirect:/";
    } else {
      // TODO: inputの内容を消さずにエラー表記させたい
      return "accountCreate";
    }
  }
}

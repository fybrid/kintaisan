package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyosaka.kintaisan.service.UserAccountService;
import com.kyosaka.kintaisan.service.UserAccountService.SigninStatus;


@Controller
@RequestMapping("/api")
public class AuthController {

  private final UserAccountService userAccountService;

  public AuthController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @PostMapping("/signin")
  public String signin(@RequestParam String username, @RequestParam String password, Model model) {

    UserAccountService.SigninResult result = userAccountService.signin(username, password);
    if (result.status() == SigninStatus.SUCCESS) {
      return "redirect:/";
    }

    if (result.status() == SigninStatus.BAD_REQUEST) {
      model.addAttribute("errorMessage", "ユーザーID/ユーザー名とパスワードを入力してください。");
      return "login";
    }

    model.addAttribute("errorMessage", "ユーザーID/ユーザー名、またはパスワードが違います。");
    return "login";
  }

}

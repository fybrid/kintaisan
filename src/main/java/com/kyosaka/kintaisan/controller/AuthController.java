package com.kyosaka.kintaisan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyosaka.kintaisan.service.UserAccountService;
import com.kyosaka.kintaisan.service.UserAccountService.SigninStatus;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/api")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  private final UserAccountService userAccountService;

  public AuthController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @PostMapping("/signin")
  public String signin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

    UserAccountService.SigninResult result = userAccountService.signin(username, password);
    if (result.status() == SigninStatus.SUCCESS) {
      session.setAttribute("userId", result.userId());
      session.setAttribute("roleId", result.roleId());
      logger.info("ログイン成功 sessionId={}, userId={}, roleId={}", session.getId(), result.userId(), result.roleId());
      return "redirect:/";
    }

    if (result.status() == SigninStatus.BAD_REQUEST) {
      model.addAttribute("errorMessage", "ユーザーID/ユーザー名とパスワードを入力してください。");
      return "login";
    }

    model.addAttribute("errorMessage", "ユーザーID/ユーザー名、またはパスワードが違います。");
    return "login";
  }

  @PostMapping("/signout")
  public String signout(HttpSession session) {
    logger.info("ログアウト sessionId={}", session.getId());
    session.invalidate();
    return "redirect:/login";
  }

}

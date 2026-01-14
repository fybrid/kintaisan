package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyosaka.kintaisan.service.UserAccountService;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

  private final UserAccountService userAccountService;

  public AdminAccountController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("/users/list")
  public String getUser(Model model) {
    model.addAttribute("accounts", userAccountService.getUser());
    return "accountList";
  }
}

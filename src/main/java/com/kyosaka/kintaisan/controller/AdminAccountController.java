package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @PostMapping("/users/delete")
  public String deleteUser(@RequestParam String userId) {
    if (userAccountService.deleteUser(userId)) {
      return "redirect:/admin/users/list";
    }
    return "redirect:/admin/users/list";
  }
}

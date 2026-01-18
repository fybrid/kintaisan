package com.kyosaka.kintaisan.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.service.UserAccountService;


@Controller
@RequestMapping("/admin")
public class AdminEditController {

  private final UserAccountService userAccountService;

  public AdminEditController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("/edit")
  public String showEditPage(@RequestParam String userId, Model model) {
    if (userId == null || userId.isBlank()) {
      return "redirect:/admin/users/list";
    }

    Optional<UserAccount> accountOpt = userAccountService.findUserAccount(userId);
    Optional<UserProfile> profileOpt = userAccountService.findUserProfile(userId);
    if (accountOpt.isEmpty() || profileOpt.isEmpty()) {
      return "redirect:/admin/users/list";
    }

    model.addAttribute("account", accountOpt.get());
    model.addAttribute("profile", profileOpt.get());
    model.addAttribute("departments", userAccountService.getDepartments());
    model.addAttribute("workplaces", userAccountService.getWorkplaces());
    return "accountEdit";
  }

  @PostMapping("/edit")
  public String editUser() {
    return "redirect:/admin/users/list";
  }


}

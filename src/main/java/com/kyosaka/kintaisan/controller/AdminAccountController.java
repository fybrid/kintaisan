package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyosaka.kintaisan.service.UserAccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

  private final UserAccountService userAccountService;

  public AdminAccountController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("/users/list")
  public String getUser(Model model, HttpSession session) {
    Object roleIdObj = session.getAttribute("roleId");
    Short sessionRoleId = null;

    // Intで入っていてもshortに直す処理
    if (roleIdObj instanceof Short) {
      sessionRoleId = (Short) roleIdObj;
    } else if (roleIdObj instanceof Integer) {
      sessionRoleId = ((Integer) roleIdObj).shortValue();
    }

    if ((roleIdObj == null) || (sessionRoleId == 1) ){
      return "login";
    }

    model.addAttribute("accounts", userAccountService.getUser(sessionRoleId));
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

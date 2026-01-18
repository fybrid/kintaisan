package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin")
public class AdminEditController {

  @GetMapping("/edit")
  public String showEditPage() {
      return "accountEdit";
  }

  @PostMapping("/edit")
  public String editUser() {
    return "redirect:/admin/users/list";
  }


}

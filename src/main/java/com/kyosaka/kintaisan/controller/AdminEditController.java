package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin")
public class AdminEditController {

  @GetMapping("/edit")
  public String showEditPage() {
      return "accountEdit";
  }

}

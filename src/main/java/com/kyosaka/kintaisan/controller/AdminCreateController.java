package com.kyosaka.kintaisan.controller;

import com.kyosaka.kintaisan.dto.UserAccountCreateRequest;
import com.kyosaka.kintaisan.service.UserAccountService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin")
public class AdminCreateController {

  private final UserAccountService userAccountService;

  AdminCreateController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("/create")
	public String showCreatePage(Model model, HttpSession session){
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

    model.addAttribute("departments", userAccountService.getDepartments());
    model.addAttribute("workplaces", userAccountService.getWorkplaces());
		return "accountCreate";
	}

  @PostMapping("/create")
  public String createUser(@ModelAttribute UserAccountCreateRequest form) {
    if (Boolean.TRUE.equals(userAccountService.createUser(form))) {
      return "redirect:/admin/users/list";
    } else {
      // TODO: inputの内容を消さずにエラー表記させたい
      return "accountCreate";
    }
  }
}

package com.kyosaka.kintaisan.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kyosaka.kintaisan.dto.UserAccountUpdateRequest;
import com.kyosaka.kintaisan.entity.UserAccount;
import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.service.UserAccountService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminEditController {

  private final UserAccountService userAccountService;

  public AdminEditController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @GetMapping("/edit")
  public String showEditPage(@RequestParam String userId, Model model, HttpSession session) {
    if (userId == null || userId.isBlank()) {
      return "redirect:/admin/users/list";
    }
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
  public String editUser(@ModelAttribute UserAccountUpdateRequest form, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

    UserAccountService.EditResult result = userAccountService.editUser(form);

    if (result.status() == true) {
      return "redirect:/admin/users/list";
    } else {
      // TODO:エラーの場合の表示
      model.addAttribute("errorMessage", result.log());
      String userId = form.getUserId();
      session.setAttribute("userIdEdit",userId);
      session.setAttribute("name",form.getName());
      session.setAttribute("departmentId",form.getDepartmentId());
      session.setAttribute("workplaceId",form.getWorkplaceId());
      session.setAttribute("phoneNumber",form.getPhoneNumber());
      session.setAttribute("email",form.getEmail());
      // redirectAttributes.addAttribute("userId", form.getUserId());
      // return "redirect:/admin/edit";
      return showEditPage(userId, model, session);
    }
  }


}

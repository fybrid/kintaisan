package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import com.kyosaka.kintaisan.service.UserProfileService;

@Controller
public class UserUpdateController {

    // インスタンス
    private final UserProfileService userProfileService;

    // コンストラクタ
    public UserUpdateController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/tellandplace")
    public String tellandplace(Model model, HttpSession session) {
        // 勤務先をセレクトボックスで選択できるようにUserIdから勤務先を取得しHTMLへ送る
        // 連絡先も同様に取得しHTMLへ送る
        Object userIdO = session.getAttribute("userId");
        if (userIdO == null) {
            return "redirect:/login";
        }
        String userId = (String) userIdO;
        System.out.println("userId: " + userId);
        // ユーザーが登録している勤務先を取得
        model.addAttribute("workplaceId", userProfileService.getUserWorkplace(userId));
        // System.out.println("workplaceId: " + userProfileService.getUserWorkplace(userId));
        
        // workplacesから勤務先を取得
        model.addAttribute("selectAll", userProfileService.getworkplaces());

        // ユーザーが登録している連絡先を取得
        model.addAttribute("contact", userProfileService.getphone(userId));
        System.out.println("contact: " + userProfileService.getphone(userId));

        return "tellandplace";
    }
    @PostMapping("/updateUser")
    public String updateUser(@RequestParam String workplace, @RequestParam String contact) {
        // 取得した勤務先と連絡先をuser_profilesに更新する
        return "redirect:/attendance_input";
    }
}

package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyosaka.kintaisan.service.UserProfileService;

@Controller
public class AttendanceController {

    // インスタンス
    private final UserProfileService userProfileService;

    // コンストラクタ
    public AttendanceController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    // 勤怠入力画面に遷移するメソッド。勤怠状況と勤務先をthymeleafでHTMLに送る。
    @GetMapping("/attendance_input")
    public String inputAttendance(Model model) {
        // attendance_recordsからuseridの勤怠状況を取得
        // user_profilesからworkplaceを取得

        model.addAttribute("test", userProfileService.test("imoto"));
        return "attendance_input";
    }
}

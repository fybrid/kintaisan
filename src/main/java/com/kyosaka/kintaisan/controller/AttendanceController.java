package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kyosaka.kintaisan.service.AttendanceService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AttendanceController {

    // インスタンス
    private final AttendanceService attendanceService;

    // コンストラクタ
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 勤怠入力画面に遷移するメソッド。勤怠状況と勤務先をthymeleafでHTMLに送る。
    @GetMapping("/attendance_input")
    // sessionからuserIdを取得できるようになったら引数にHttpSession sessionを追加する
    public String inputAttendance(Model model, HttpSession session) {
        // String userId = "imoto"; // ログイン機能できるまで仮置き
        Object userIdO = session.getAttribute("userId"); // ログイン機能できるまで仮置き
        if (userIdO == null) {
            return "redirect:/login";
        }
        String userId = (String) userIdO;
        System.out.println("userId: " + userId);
        // String username = (String) session.getAttribute("username"); // ログイン機能できるまで仮置き
        model.addAttribute("name", "username"); // 勤怠入力時に表示したい
        
        // attendance_recordsからuseridの勤怠状況を取得
        // 1:出勤ボタン、0:退勤ボタン、2:退勤済みボタン
        model.addAttribute("status", attendanceService.isAttendance(userId));

        // user_profilesからworkplaceを取得
        // セレクトボックスのデフォルト値として使用
        model.addAttribute("workplaceId", attendanceService.getUserWorkplace(userId));

        // workplacesから全勤務先を取得
        model.addAttribute("selectAll", attendanceService.getworkplaces());

        return "attendance_input";
    }
    @PostMapping("/stamp")
    public String stamp(@RequestParam(required = false) int workplaceId, Model model, HttpSession session) {
        // 取得したGetを表示
        // model.addAttribute("test", );
        Object userIdO = session.getAttribute("userId");
        if (userIdO == null) {
            return "redirect:/login";
        }
        String userId = (String) userIdO;
        if (attendanceService.stamp(userId, workplaceId)) {
            System.out.println("出勤処理完了");
        } else {
            System.out.println("退勤処理完了");
        }
        return "redirect:/attendance_status";
    }

    @GetMapping("/attendance_status")
    public String attendance_status() {
        return "attendance_status";
    }
    
}

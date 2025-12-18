package com.kyosaka.kintaisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kyosaka.kintaisan.service.AttendanceService;
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
    public String inputAttendance(Model model) {
        
        String userId = "imoto"; // ログイン機能できるまで仮置き
        String username = "いもと"; // ログイン機能できるまで仮置き
        model.addAttribute("name", username); // 勤怠入力時に表示したい
        
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
    public String stamp(@RequestParam(required = false) int workplaceId, Model model) {
        // 取得したGetを表示
        // model.addAttribute("test", );
        if (attendanceService.stamp("imoto", workplaceId)) {
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

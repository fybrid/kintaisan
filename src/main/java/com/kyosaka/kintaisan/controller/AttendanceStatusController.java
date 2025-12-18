package com.kyosaka.kintaisan.controller;

import com.kyosaka.kintaisan.service.AttendanceStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class AttendanceStatusController {

    private final AttendanceStatusService service;

    public AttendanceStatusController(AttendanceStatusService service) {
        this.service = service;
    }

    @GetMapping("/attendanceStatus")
    public String attendanceStatus(Model model) {

        model.addAttribute("attendanceList", service.getTodayAttendanceStatus());

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("M月d日（E）", Locale.JAPANESE);
        model.addAttribute(
            "formattedDate",
            LocalDate.now().format(formatter)
        );

        model.addAttribute("departmentName", "営業部");//html表示用の仮

        return "attendance_status";
    }
}

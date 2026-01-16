package com.kyosaka.kintaisan.controller;

import com.kyosaka.kintaisan.service.AttendanceService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.servlet.http.HttpSession;

@Controller
public class AttendanceRecordController {

        private final AttendanceService service;

        public AttendanceRecordController(AttendanceService service) {
                this.service = service;
        }

        @GetMapping("/attendance_status")
        public String attendanceStatus(Model model, Authentication authentication, HttpSession session) {

                Object userIdO = session.getAttribute("userId");
                    if (userIdO == null) {
                    return "redirect:/login";
                }

                String userId = (String) userIdO;

                model.addAttribute("attendanceList", service.getTodayAttendanceStatus(userId));

                model.addAttribute("departmentName", service.getDepartmentNameByUserId(userId));

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("M月d日（E）", Locale.JAPANESE);

                model.addAttribute("formattedDate", LocalDate.now().format(formatter));

                return "attendance_status";
                }
}

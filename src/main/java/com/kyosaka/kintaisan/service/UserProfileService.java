package com.kyosaka.kintaisan.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyosaka.kintaisan.entity.Attendance;
import com.kyosaka.kintaisan.repository.AttendanceRepository;

@Service
public class UserProfileService {

    // AttendanceRepositoryのインスタンス
    private final AttendanceRepository attendanceRepository;

    // コンストラクタ
    public UserProfileService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    // テスト用メソッド
    public String test(String userId){
        // テーブルの接続テスト
        Optional<Attendance> test = attendanceRepository.findByUserId(userId);
        String ret = "";
        if (test.isPresent()) {
            Attendance data = test.get();
            ret = data.getUpdatedAt().toString();
        }else{
            // データが見つからなかった場合の処理
            ret = "no data";
        }
        return ret;
    }
    public boolean stamp(String userId){
        Boolean isSuccess = true;
        // セッションでユーザーIDがある前提
        String userid = "imoto";
        // 打刻する
        return isSuccess;
    }
}

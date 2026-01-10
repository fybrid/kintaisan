package com.kyosaka.kintaisan.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.kyosaka.kintaisan.entity.Attendance;
import com.kyosaka.kintaisan.entity.UserProfile;
import com.kyosaka.kintaisan.entity.workplaces;
import com.kyosaka.kintaisan.repository.AttendanceRepository;
import com.kyosaka.kintaisan.repository.UserProfileRepository;
import com.kyosaka.kintaisan.repository.WorkplacesRepository;

@Service
public class AttendanceService {
    // AttendanceRepositoryのインスタンス
    private final AttendanceRepository attendanceRepository;
    private final WorkplacesRepository workplacesRepository;
    private final UserProfileRepository userProfileRepository;

    // コンストラクタ
    public AttendanceService(AttendanceRepository attendanceRepository, WorkplacesRepository workplacesRepository, UserProfileRepository userProfileRepository) {
        this.attendanceRepository = attendanceRepository;
        this.workplacesRepository = workplacesRepository;
        this.userProfileRepository = userProfileRepository;
    }
    // テスト用メソッド
    public String test(String userId){
        // テーブルの接続テスト
        Optional<Attendance> test = attendanceRepository.findByUserId(userId);
        String ret = "";
        if (test.isPresent()) {
            Attendance data = test.get();
            ret = data.getWorkplaceId() + "";
        }else{
            // データが見つからなかった場合の処理
            ret = "no data";
        }
        return ret;
    }

    //---------- Attendance_input ----------
    // workplaces取得メソッド
    public List<workplaces> getworkplaces(){
        return workplacesRepository.findAll();
    }
    // 勤怠状況取得メソッド
    // 今日のレコードがあれば出勤済み、なければ未出勤とする
    public Integer isAttendance(String userId){
        Optional<Attendance> attendanceOpt = attendanceRepository.findTopByUserIdOrderByWorkDateDesc(userId);
        int ret = 1;
        if (attendanceOpt.isEmpty()) {
            return ret;
        }
        Attendance attendance = attendanceOpt.get();
        if(attendance.getWorkDate().equals(LocalDate.now())){
            // 退勤表示
            // もし退勤時間がnullの場合は退勤済み表示
            if(attendance.getClockoutTime() == null) ret = 0;
            else ret = 2;
        }else{
            // System.out.println("未出勤");
            // 出勤表示
            ret = 1;
        }
        
        return ret;
    }
    // ユーザーの直近で利用した勤務先取得メソッド
    public int getUserWorkplace(String userId){
        Optional<UserProfile>up = userProfileRepository.findByUserId(userId);
        // return up.get().getWorkplaceId();
        return up.map(UserProfile::getWorkplaceId).orElse(null);
    }

    // 打刻処理メソッド
    public boolean stamp(String userId, int workplaceId){
        Boolean isSuccess = true;
        System.out.println("stampメソッド実行");
        Optional<UserProfile> profileOpt =
        userProfileRepository.findByUserId(userId);

        if (profileOpt.isEmpty()) {
            return false;
        }
        UserProfile profile = profileOpt.get();
        Integer retAttendance = isAttendance(userId);
        int status = (retAttendance == null) ? -1 : retAttendance;
        switch (status) {
            case 1:
                // 出勤処理
                Attendance attendance = new Attendance();
                System.out.println("出勤処理実行");
                // 年月日, ユーザid, departmentIdを入れる
                // 例) 20251217001001　みたいな
                // 先にuserId(string)からuser_profilesを参照してdepartmentId(int)と主キーを取得
                String departmentId = String.format("%03d",userProfileRepository.findByUserId(userId).get().getDepartmentId());
                String id = String.format("%03d", userProfileRepository.findByUserId(userId).get().getId());
            
                String recordId = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + departmentId + id;
                System.out.println(recordId);
                // 入力する値を確認
                System.out.println("recordId: " + recordId);
                System.out.println("userId: " + userId);
                System.out.println("workDate: " + LocalDate.now());
                System.out.println("clockinTime: " + LocalTime.now());
                System.out.println("workplaceId: " + workplaceId);
                System.out.println("updatedAt: " + LocalDate.now());

                attendance.setRecordId(Long.parseLong(recordId));
                attendance.setUserId(userId);
                attendance.setWorkDate(LocalDate.now());
                attendance.setClockinTime(ZonedDateTime.now());
                attendance.setWorkplaceId(workplaceId);
                attendance.setUpdatedAt(ZonedDateTime.now());
                attendanceRepository.save(attendance);
                break;
            case 0:
                // 退勤処理
                System.out.println("退勤処理実行");
                Optional<Attendance> attendanceOpt = attendanceRepository.findTopByUserIdOrderByWorkDateDesc(userId);
                if (attendanceOpt.isPresent()) {
                    Attendance saveAttendance = attendanceOpt.get();
                    saveAttendance.setClockoutTime(ZonedDateTime.now());
                    attendanceRepository.save(saveAttendance);
                } else {
                    // 出勤記録が見つからない場合のエラー
                    isSuccess = false;
                }
                break;
            case 2:
                // 正常に動けばこの値が入ることはない
                break;
            default:
                System.out.println("エラー：打刻処理失敗");
                break;
        }
        System.out.println("打刻処理完了");

        return isSuccess;
    }

    //---------- Attendance_status ----------
}

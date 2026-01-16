package com.kyosaka.kintaisan.repository;

import com.kyosaka.kintaisan.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceStatusRepository
        extends JpaRepository<Attendance, Integer> {

    @Query(value = """
        SELECT
            ua.user_id       AS userId,
            ua.name          AS userName,
            up.phone_number  AS phoneNumber,
            up.email         AS email,
            w.workplace_name AS workplaceName,
            a.clockin_time   AS clockinTime,
            a.clockout_time  AS clockoutTime
        FROM attendance_records a
        JOIN user_accounts ua ON a.user_id = ua.user_id
        JOIN user_profiles up ON a.user_id = up.user_id
        JOIN workplaces w ON a.workplace_id = w.workplace_id
        WHERE a.work_date = :workDate AND up.department_id = :departmentId
        """,
        nativeQuery = true)
    List<Object[]> findTodayStatusRaw(LocalDate workDate, int departmentId);
}

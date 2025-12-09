package com.kyosaka.kintaisan.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyosaka.kintaisan.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findByUserId(String userId);
    Optional<Attendance> findByWorkDate(LocalDate workDate);
    Optional<Attendance> findByClockinTime(LocalTime clockIn);
}



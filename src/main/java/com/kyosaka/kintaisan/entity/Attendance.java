package com.kyosaka.kintaisan.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attendance_records")
public class Attendance {
    @Id
    private int id;
    @Column(name = "record_id")
    private int recordId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "work_date")
    private LocalDate workDate;
    @Column(name = "clockin_time")
    private LocalTime clockinTime;
    @Column(name = "clockout_time")
    private LocalTime clockoutTime;
    @Column(name = "workplace_id")
    private int workplaceId;
    @Column(name = "updated_at")
    private Date updatedAt;
}

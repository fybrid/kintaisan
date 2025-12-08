package com.kyosaka.kintaisan.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance_records")
public class Attendance {
    @Id
    private int id;
    @Column(name = "recordid")
    private int recordid;
    @Column(name = "userid")
    private String userid;
    @Column(name = "workdate")
    private LocalDate workDate;
    @Column(name = "clockin")
    private LocalTime clockIn;
    @Column(name = "clockout")
    private LocalTime clockOut;
    @Column(name = "workplaceid")
    private int workplaceId;

}

package com.kyosaka.kintaisan.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "attendance_records")
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "record_id", nullable = false, unique = true)
    private Long recordId;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "work_date", nullable = false)//自動更新じゃないから他でやる
    private LocalDate workDate;

    @Column(name = "clockin_time", nullable = false)
    private ZonedDateTime clockinTime;

    @Column(name = "clockout_time")
    private ZonedDateTime clockoutTime;

    @Column(name = "workplace_id", nullable = false)
    private Integer workplaceId;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @PrePersist
    @PreUpdate//作成時と更新時にupdatedAtを書き換える
    protected void onUpdate() {
        updatedAt = ZonedDateTime.now();
    }
}
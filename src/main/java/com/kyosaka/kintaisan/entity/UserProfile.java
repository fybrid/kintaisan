package com.kyosaka.kintaisan.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true, length = 50)
    private String userId;

    @Column(name = "department_id", nullable = false)
    private int departmentId;

    @Column(name = "workplace_id", nullable = false)
    private int workplaceId;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist//作成時にcreatedAt, updatedAtを登録する
    protected void onCreate(){
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    @PreUpdate//更新時にupdatedAtを書き換える
    protected void onUpdate(){
        updatedAt = ZonedDateTime.now();
    }
}

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
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false, unique = true, length = 50)
    private String userId;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "name", unique = true, length = 20, nullable = false)
    private String name;

    @Column(name = "role_id")
    private short roleId;

    @Column(name = "is_active")
    private Boolean isActive;

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

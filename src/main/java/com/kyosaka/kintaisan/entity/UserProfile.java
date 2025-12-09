package com.kyosaka.kintaisan.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "department_id")
    private int departmentId;
    @Column(name = "workplace_id")
    private int workplaceId;
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}

package com.kyosaka.kintaisan.dto;

import lombok.Data;

@Data
public class UserAccountUpdateRequest {

    // UserAccount
    private String userId;
    private String password;
    private String check;
    private String name;
    private Boolean admin;

    // UserProfile
    private Integer departmentId;
    private Integer workplaceId;
    private String phoneNumber;
    private String email;
}

package com.kyosaka.kintaisan.dto;

// import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class UserAccountCreateRequest {

    private String userId;

    // UserAccount
    private String password;
    private String name;
    private Boolean admin;

    // UserProfile
    private Integer departmentId;
    private Integer workplaceId;
    private Integer phoneNumber;
    private String email;
}

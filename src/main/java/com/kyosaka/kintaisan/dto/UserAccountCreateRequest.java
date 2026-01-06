package com.kyosaka.kintaisan.dto;

// import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class UserAccountCreateRequest {

    private String userId;

    // UserAccount
    private String password;
    private String name;
    private Short roleId;

    // UserProfile
    private Integer departmentId;
    private Integer workplaceId;
    private Integer phoneNumber;
    private String email;
}

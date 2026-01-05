package com.kyosaka.kintaisan.dto;

// import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class UserAccountCreateRequest {
    private Integer id;
    private String userId;
    private String password;
    private String name;
    private Short roleId;
}

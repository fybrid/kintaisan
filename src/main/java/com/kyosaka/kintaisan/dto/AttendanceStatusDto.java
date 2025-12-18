package com.kyosaka.kintaisan.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceStatusDto {

    private String userId;
    private String userName;
    private String phoneNumber;
    private String email;
    private String workplaceName;
    private ZonedDateTime clockinTime;
    private ZonedDateTime clockoutTime;
}

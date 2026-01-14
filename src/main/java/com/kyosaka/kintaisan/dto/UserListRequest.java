package com.kyosaka.kintaisan.dto;

import lombok.Data;

@Data
public class UserListRequest {
  private String userId;
  private String name;
  private String departmentName;
}

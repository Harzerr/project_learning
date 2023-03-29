package com.example.springboot.controller.DTO;

import lombok.Data;

@Data
public class UserQueryDTO {
    private Integer currentPage;
    private Integer pageCount;
    private Integer pageSize;
    private String username;
    private String email;
    private String phone;

}

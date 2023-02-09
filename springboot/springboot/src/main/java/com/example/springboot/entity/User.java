package com.example.springboot.entity;

import lombok.Data;

//使用lombok自动生成Bean
@Data
public class User {
//    账号
    private Integer id;
//    用户名
    private String username;
//    密码
    private String password;
}

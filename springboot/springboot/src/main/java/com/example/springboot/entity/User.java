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
    //    性别
    private String sex;
    //    年龄
    private Integer age;
    //    手机
    private String phone;
    //    邮箱
    private String email;
    //    地址
    private String address;
    //    头像
    private String avatar;
    //    个性宣言
    private String statement;
}

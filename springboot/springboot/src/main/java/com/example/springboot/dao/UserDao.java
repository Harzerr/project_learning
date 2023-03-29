package com.example.springboot.dao;

import com.example.springboot.controller.DTO.UserQueryDTO;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> findall();
    User getByID(Integer id);

    User getByUser(@Param("username") String username, @Param("password") String password);

    int insert(User user);

    int update(User user);

    int deleteById(Integer id);

    List<User> findPage(UserQueryDTO userQueryDTO);



    long count(UserQueryDTO userQueryDTO);

    List<User> findByOptions(UserQueryDTO userQueryDTO);
}

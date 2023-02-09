package com.example.springboot;

import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController//组合式注解，专用于开发Restful接口
@RequestMapping("/user")
public class SpringbootApplication {
    @Autowired
    private UserDao userDao;
    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return userDao.getByID(id);
    }
    @GetMapping
    public List<User> findAll(){

        return userDao.findall();
    }

    @GetMapping("/uniqueQuery")
    public User uniqueQuery(String username, String password){
        return userDao.getByUser(username, password);
    }

    @PostMapping
    public int save(@Validated @RequestBody User user){
//        @Validated增加判断，增强健壮性,自写逻辑也可以
        if(user.getUsername()==null || user.getPassword() == null){
            throw new RuntimeException("参数为空");
        }
        return userDao.insert(user);
    }

    @PutMapping
    public void update(@RequestBody User user){
        if(user.getId() == null){
            throw new RuntimeException("参数为空");
        }
         userDao.update(user);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        if(id == null || id == 0){
            throw new RuntimeException("参数为空");
        }
        return userDao.deleteById(id) == 1;
    }
}

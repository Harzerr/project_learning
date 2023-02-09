package com.example.springboot.controller;

        import com.example.springboot.dao.UserDao;
        import com.example.springboot.entity.User;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;

        import javax.annotation.Resource;
        import java.util.ArrayList;
        import java.util.List;

@SpringBootApplication
@RestController//组合式注解，专用于开发Restful接口
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;


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

    @PostMapping("/login")
    public User login(@Validated @RequestBody User user){
        if(user.getUsername()==null || user.getPassword() == null){
            throw new RuntimeException("参数为空");
        }
        User ret = userDao.getByUser(user.getUsername(), user.getPassword());
        if(ret == null ){
            throw new RuntimeException("用户名或密码错误");
        }
        return ret;
    }
}

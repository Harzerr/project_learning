package com.example.springboot.controller;

        import com.example.springboot.common.Result;
        import com.example.springboot.controller.DTO.UserQueryDTO;
        import com.example.springboot.dao.UserDao;
        import com.example.springboot.entity.User;
        import com.github.pagehelper.PageHelper;
        import com.github.pagehelper.PageInfo;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;

        import javax.annotation.Resource;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@SpringBootApplication
@RestController//组合式注解，专用于开发Restful接口
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;


    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        return Result.success(userDao.getByID(id));
    }
    @GetMapping
    public Result findAll(){
        return Result.success(userDao.findall());
    }

    @GetMapping("/page")// /user/page？currentPage=1&pageSize=10
    public Result findPage(UserQueryDTO userQueryDTO){
        Integer currentPage = userQueryDTO.getCurrentPage();
        Integer pageSize = userQueryDTO.getPageSize();


        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage - 1) * pageSize;
        userQueryDTO.setPageCount(pageNum);
        List<User> users = userDao.findPage(userQueryDTO);
        long totalItem = userDao.count(userQueryDTO);
        Map<String,Object> map = new HashMap<>();
        map.put("data", users);
        map.put("totalItem", totalItem);
        return Result.success(map);

//        !!!使用pagehelper
//        PageHelper.startPage(currentPage, pageSize);
//        List<User> users = userDao.findByOptions(userQueryDTO);
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//        pageInfo.setList(users);
//        pageInfo.setPageNum(currentPage);
//        pageInfo.setPages(pageSize);
//        return Result.success(pageInfo);


    }

    @GetMapping("/uniqueQuery")
    public Result uniqueQuery(String username, String password){
        return Result.success(userDao.getByUser(username, password));
    }

    @PostMapping
    public Result save(@Validated @RequestBody User user){
//        @Validated增加判断，增强健壮性,自写逻辑也可以
        if(user.getUsername()==null || user.getPassword() == null){
            Result.error("参数为空");
        }

        return Result.success(userDao.insert(user));
    }

    @PutMapping
    public Result update(@RequestBody User user){
        if(user.getId() == null){
            Result.error("参数为空");
        }

        return Result.success(userDao.update(user));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if(id == null || id == 0){
            return Result.error("账号不存在");
        }
        return Result.success(userDao.deleteById(id) == 1);
    }

    @PostMapping("/login")
    public Result login(@Validated @RequestBody User user){
        if(user.getUsername()==null || user.getPassword() == null){
            return Result.error("参数为空");
        }
        User ret = userDao.getByUser(user.getUsername(), user.getPassword());
        if(ret == null ){
            return Result.error("用户名或密码错误");
        }
        return Result.success(ret);
    }
}

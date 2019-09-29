package com.kedacom.controller;

import com.kedacom.annotation.WebLog;
import com.kedacom.domain.User;
import com.kedacom.service.Impl.UserServiceImpl;
import com.kedacom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kedacom on 2019/8/2.
 */
@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;
    //	增加用户
    @PostMapping
    public User save(@RequestBody User user){
        return (User) userService.saveUser(user);
    }
    @PutMapping
    public User update(@RequestBody User user){
        return (User) userService.saveUser(user);
    }
    @DeleteMapping("/id/{id}")
    public String delete(@PathVariable int id){
        userService.deleteById(id);
        return "删除成功！";
    }
    @GetMapping("/all")
    @WebLog
    public String findAll(){
        //return userService.queryAll();
        return "ok!!";
    }
    @GetMapping("/id/{id}")
    @WebLog
    public  User findById(@PathVariable int id ){
        return  userService.getById(id);
    }


}

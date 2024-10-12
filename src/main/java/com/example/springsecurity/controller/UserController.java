package com.example.springsecurity.controller;

import com.example.springsecurity.entity.Users;
import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public Users register (@RequestBody Users user) throws Exception {
        return  userService.register(user);
    }

    @PostMapping("/login")
    public String login (@RequestBody Users user) throws Exception {
       return userService.verify(user);
    }

}

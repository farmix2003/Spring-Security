package com.example.springsecurity.service;


import com.example.springsecurity.entity.Users;

import java.util.List;

public interface UserService {

    public List<Users> findAll();

    Users register(Users user) throws Exception;

    String verify(Users user);
}

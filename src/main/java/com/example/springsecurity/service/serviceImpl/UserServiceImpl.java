package com.example.springsecurity.service.serviceImpl;

import com.example.springsecurity.entity.Users;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users register(Users user) throws Exception {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("Please fill all fields");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

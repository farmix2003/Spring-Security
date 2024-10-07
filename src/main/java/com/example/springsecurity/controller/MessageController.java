package com.example.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class MessageController {

    @GetMapping("/message")
   public ResponseEntity<String> message() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}

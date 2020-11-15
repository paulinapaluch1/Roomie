package com.pm.roomie.controller;


import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.json.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    public MobileController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("login/{username}/{password}")
    public User loginToMobileApp(@PathVariable String username, @PathVariable("password") String password) {
       User user = userRepository.findByLogin(username);
       if(null != user && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }else{
           return new User();
        }

    }






}

package com.pm.roomie.controller;


import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.FlatRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.json.Flat;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mobile")
public class MobileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FlatMemberRepository flatMemberRepository;
    @Autowired
    FlatRepository flatRepository;
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
       user.setFlatMemberList(null);
       if(null != user && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }else{
           return new User();
        }

    }

    @GetMapping("getFlatmates/{userId}")
    public List<User> getFlatmates(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<FlatMember> flatMembersList = flatMemberRepository.findByFlat(member.getFlat());
        ArrayList<User> flatmates = new ArrayList<>();
        for(FlatMember m :flatMembersList){
            User user1 = userRepository.findUserById(m.getUser().getId());
            if(user1.isActive() && user1.getId()!=user.getId()){
            flatmates.add(user1);
            }
        }
        for (User u: flatmates ) {
            u.setFlatMemberList(null);
        }
        return flatmates;
    }

    @PostMapping("archive/{id}")
    public Boolean archiveUser(@PathVariable Integer id) {
        User user = userRepository.findUserById(id);
        user.setActive(false);
        userRepository.save(user);
        return true;
    }


}

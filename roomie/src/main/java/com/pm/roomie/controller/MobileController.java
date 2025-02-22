package com.pm.roomie.controller;


import com.pm.roomie.dao.BillRepository;
import com.pm.roomie.dao.BillTypeRepository;
import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.FlatRepository;
import com.pm.roomie.dao.MembersBillRepository;
import com.pm.roomie.dao.TimetableRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.dtos.TimetableObject;
import com.pm.roomie.json.*;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.MembersBill;
import com.pm.roomie.json.User;
import com.pm.roomie.json.Flat;
import com.pm.roomie.json.Bill;
import com.pm.roomie.json.BillType;
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
    BillRepository billRepository;
    
    @Autowired
    BillTypeRepository billTypeRepository;
    
    @Autowired
    FlatRepository flatRepository;
    
    @Autowired
    MembersBillRepository membersBillRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TimetableRepository timetableRepository;
        
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


    @PostMapping("saveUser/{flatId}")
    public Boolean saveUser(@RequestBody User user,@PathVariable Integer flatId) {
        User userDb = userRepository.findByLogin(user.getLogin());
        if(userDb == null){
            user.setActive(true);
            user.setRoles("user");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            FlatMember flatMember = new FlatMember();

            userRepository.save(user);
            flatMember.setUser(user);
            flatMember.setFlat(flatRepository.findFlatById(1));
            flatMemberRepository.save(flatMember);

            return true;
        }else{
            return false;
        }

    }


    @GetMapping("getUserById/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = userRepository.findUserById(id);
        return user;
    }

    @PostMapping("updateUser")
    public Boolean updateUser(@RequestBody User user) {
        User userDb = userRepository.findByLogin(user.getLogin());
        if(userDb == null){
            return false;
        }else{
            userDb.setLogin(user.getLogin());
            userDb.setPhone(user.getPhone());
            userDb.setName(user.getName());
            userDb.setSurname(user.getSurname());
            if(user.getPassword() != null){
                userDb.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(userDb);
            return true;
        }

    }    
}

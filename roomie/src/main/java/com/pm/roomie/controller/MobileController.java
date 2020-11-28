package com.pm.roomie.controller;


import com.pm.roomie.dao.BillRepository;
import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.FlatRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.User;
import com.pm.roomie.json.Flat;
import com.pm.roomie.json.Bill;
import com.pm.roomie.json.BillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("getBills/{userId}")
    public List<Bill> getBills(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<Bill> billsList = billRepository.findByFlat(member.getFlat());
        
        BillType billType;
        
        return billsList;
    }
    
//    @GetMapping("getFlats/{userId}")
//    public List<Flat> getFlats(@PathVariable Integer userId) {
//        User user = userRepository.findUserById(userId);
//        List<Flat> flatList = flatRepository.findByUser(user);
//        ArrayList<Flat> flatBills = new ArrayList<>();
//        for(Flat bill :billsList){
//            flatBills.add(flatRepository.findFlatById(bill.getFlat().getId()));
//        }
//        for (Flat f: flatBills ) {
//            f.setBillList(null);
//        }
//        return flatBills;
//    }

//    @GetMapping("getBills/{flatId}")
//    public List<Flat> getFlatBills(@PathVariable Integer flatId) {
//        Flat flat = flatRepository.findFlatById(flatId);
//        List<Bill> billsList = billRepository.findByFlat(flat);
//        ArrayList<Flat> flatBills = new ArrayList<>();
//        for(Bill bill :billsList){
//            flatBills.add(flatRepository.findFlatById(bill.getFlat().getId()));
//        }
//        for (Flat f: flatBills ) {
//            f.setBillList(null);
//        }
//        return flatBills;
//    }
    
//    @GetMapping("getBills/{flatId}")
//    public List<Bill> getFlatBills(@PathVariable Integer flatId) {
//        Flat flat = flatRepository.findFlatById(flatId);
//        List<Bill> billsList = billRepository.findByFlat(flat);
//        ArrayList<Flat> flatBills = new ArrayList<>();
//        for(Bill bill :billsList){
//            flatBills.add(flatRepository.findFlatById(bill.getFlat().getId()));
//        }
//        for (Flat f: flatBills ) {
//            f.setBillList(null);
//        }
//        return flatBills;
//    }
    
//    @GetMapping("getBills/{flatmemberId}")
//    public List<MembersBill> getMemberBills(@PathVariable Integer userId) {
//        User user = userRepository.findFlatById(flatId);
//        List<Bill> billsList = billRepository.findByFlat(flat);
//        ArrayList<Flat> bills = new ArrayList<>();
//        for(Bill bill :billsList){
//            bills.add(flatRepository.findFlatById(bill.getFlat().getId()));
//        }
//        for (Flat f: bills ) {
//            f.setBillList(null);
//        }
//        return bills;
//    }


}

package com.pm.roomie.controller;

import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.FlatRepository;
import com.pm.roomie.dao.TimetableRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.dtos.FlatMemberObject;
import com.pm.roomie.dtos.TimetableObject;
import com.pm.roomie.json.Flat;
import com.pm.roomie.json.BillType;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.Timetable;
import com.pm.roomie.json.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class TimetableController {
    
    @Autowired
    TimetableRepository timetableRepository;
    
    @Autowired
    FlatMemberRepository flatMemberRepository;
    
    @Autowired
    FlatRepository flatRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("getTimetable/{userId}")
    public List<TimetableObject> getTimetable(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        Flat flat = flatRepository.findFlatById(member.getFlat().getId());
        List<Timetable> timetableList = timetableRepository.findTimetableByFlat(flat.getId());
        
        ArrayList<TimetableObject> timetableObjectList = new ArrayList<>();
        
        for(Timetable t : timetableList){
            
            TimetableObject timetableObject = new TimetableObject();
            timetableObject.setFlatMemberName(t.getFlatMember().getUser().getName());
            timetableObject.setFlatMemberSurname(t.getFlatMember().getUser().getSurname());
            timetableObject.setDate(t.getDate());

            timetableObjectList.add(timetableObject);
        }
        
        return timetableObjectList;
    }
    
    @PostMapping("saveTimetable/{flatMemberId}")
    public Boolean saveTimetable(@RequestBody Timetable timetable, @PathVariable Integer flatMemberId) {
        
        FlatMember flatMember = flatMemberRepository.getOne(flatMemberId);
        timetable.setFlatMember(flatMember);
        timetableRepository.save(timetable);
      
        return true;
    }
    
    @GetMapping("getFlatMemberById/{id}")
    public FlatMember getFlatMemberById(@PathVariable Integer id) {
        FlatMember flatMember = flatMemberRepository.findFlatMemberById(id);
        
        return flatMember;
    }
    
    @GetMapping("getFlatmembers/{userId}")
    public List<FlatMemberObject> getFlatmembers(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);

        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<FlatMember> flatMembersList = flatMemberRepository.findByFlat(member.getFlat());
        ArrayList<FlatMemberObject> flatmates = new ArrayList<>();
        for(FlatMember m :flatMembersList){
            User user1 = userRepository.findUserById(m.getUser().getId());
            FlatMemberObject memberObject = new FlatMemberObject();
            
            memberObject.setFlatMemberName(user1.getName());
            memberObject.setFlatMemberSurname(user1.getSurname());
            memberObject.setId(m.getId());
            memberObject.setIdFlat(m.getFlat().getId());
            memberObject.setIdUser(m.getUser().getId());

            flatmates.add(memberObject);

        }
 
        return flatmates;
    }
    
//    @GetMapping("getTimetable/{userId}")
//    public List<Timetable> getTimetable(@PathVariable Integer userId) {
//        System.out.print("User id:");
//        System.out.println(userId);
//        User user = userRepository.findUserById(userId);
//        System.out.print("User:");
//        System.out.println(user);
//        FlatMember member = flatMemberRepository.findByUser(user).get(0);
//        System.out.print("Member:");
//        System.out.println(member);
//        Flat flat = flatRepository.findFlatById(member.getFlat().getId());
//        List<Timetable> timetableList = timetableRepository.findTimetableByFlat(flat.getId());
//        
//        return timetableList;
//    }
    
}

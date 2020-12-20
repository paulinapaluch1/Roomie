package com.pm.roomie.controller;

import com.pm.roomie.dao.ChecklistRepository;
import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.json.Checklist;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile")
public class ChecklistController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FlatMemberRepository flatMemberRepository;
    @Autowired
    ChecklistRepository checklistRepository;

    @GetMapping("getChecklist/{userId}")
    public List<String> getProducts(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<String> checklist = checklistRepository.findByFlatMember(member);
        return checklist;
    }


    @GetMapping("deleteItem/{item}/{idUser}")
    public Boolean deleteItem(@PathVariable String item, @PathVariable Integer idUser) {
        User user = userRepository.findUserById(idUser);
        FlatMember member = user.getFlatMemberList().get(0);
        Checklist checklist = checklistRepository.findByNameAndMember(item,member);
        checklistRepository.delete(checklist);
        return true;
    }
    @GetMapping("saveChecklistItem/{name}/{idUser}")
    public Boolean addItem(@PathVariable String name,@PathVariable Integer idUser) {
        Checklist checklist = new Checklist();
        User user = userRepository.findUserById(idUser);
        FlatMember member = user.getFlatMemberList().get(0);
        checklist.setFlatMember(member);
        checklist.setName(name);
        checklistRepository.save(checklist);
       return true;
    }
}


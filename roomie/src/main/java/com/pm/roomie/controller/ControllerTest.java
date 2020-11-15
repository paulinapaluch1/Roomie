package com.pm.roomie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class ControllerTest {


//    @GetMapping("/hello")
//    public String hello() {
//        // return userRepository.findAll();
//        return "Hello world";
//    }


    @RequestMapping(value = "/helloo", method = RequestMethod.GET)
    public String helloooo(ModelMap model) {
        return "pl";
    }

}

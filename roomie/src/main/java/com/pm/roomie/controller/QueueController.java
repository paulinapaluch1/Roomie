package com.pm.roomie.controller;

import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.ProductRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile")
public class QueueController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FlatMemberRepository flatMemberRepository;

    @GetMapping("getProducts/{userId}")
    public List<Product> getProducts(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<Product> productsList = productRepository.findAll();
        return productsList;
    }
}

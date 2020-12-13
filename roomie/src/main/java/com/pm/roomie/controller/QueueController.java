package com.pm.roomie.controller;

import com.pm.roomie.dao.FlatMemberRepository;
import com.pm.roomie.dao.ProductHistoryRepository;
import com.pm.roomie.dao.ProductRepository;
import com.pm.roomie.dao.UserRepository;
import com.pm.roomie.dtos.ProductHistoryDto;
import com.pm.roomie.dtos.QueueDto;
import com.pm.roomie.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mobile")
public class QueueController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductHistoryRepository productHistoryRepository;
    @Autowired
    FlatMemberRepository flatMemberRepository;

    @GetMapping("getProducts/{userId}")
    public List<QueueDto> getProducts(@PathVariable Integer userId) {
        User user = userRepository.findUserById(userId);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        List<Product> productsList = productRepository.findByFlatMember(member);
        List<QueueDto> queueList = new ArrayList<>();
        for(Product product : productsList ){
            QueueDto queue = new QueueDto();
            queue.setProductName(product.getName());
            queue.setUserName(calculateWhoTurnItIs(member, product));
            queueList.add(queue);
        }
        return queueList;
    }

    private String calculateWhoTurnItIs(FlatMember member,Product product) {
        List<FlatMember> list = flatMemberRepository.findByFlat(member.getFlat());
        int size = list.size();
        if (list.size() > 1) {
            List<ProductHistory> historyListForProductAndFlat = productHistoryRepository.findAllByProductAndFlat(product, member.getFlat());
            if (historyListForProductAndFlat != null) {
                if (historyListForProductAndFlat.size() < size) {
                    for (FlatMember m : list) {
                        if (!personBoughtProduct(historyListForProductAndFlat, m)) {
                            return getQueueStringForFlatMember(m);
                        }
                    }
                } else if (historyListForProductAndFlat.size() == size) {
                    FlatMember m = historyListForProductAndFlat.get(size-1).getFlatMember();
                    return getQueueStringForFlatMember(m);
                } else {
                    FlatMember m = historyListForProductAndFlat
                            .get(historyListForProductAndFlat.size()-(size-1)).getFlatMember();
                    return getQueueStringForFlatMember(m);
                }
            }
        } else {
            return getQueueStringForFlatMember(member);
        }
        return getQueueStringForFlatMember(member);
    }

    private String getQueueStringForFlatMember(FlatMember m) {
        User user = userRepository.findByFlatMember(m);
        return user.getName() + " " + user.getSurname();
    }

    private boolean personBoughtProduct(List<ProductHistory> historyListForProductAndFlat, FlatMember m) {
        return historyListForProductAndFlat
                .stream()
                .map(f -> f.getFlatMember().getId())
                .collect(Collectors.toList()).contains(m.getId());
    }

    @PostMapping("saveProduct/{id}")
    public Boolean saveUser(@RequestBody Product product ,@PathVariable Integer id) {
        User userDb = userRepository.findUserById(id);
        FlatMember member = flatMemberRepository.findByUser(userDb).get(0);
        if (userDb != null) {
            productRepository.save(product);
            addNewProductHistory(productRepository.findByName(product.getName()), member);
            return true;
        } else {
            return false;
        }
    }

    private void addNewProductHistory(Product product, FlatMember member) {
        ProductHistory prHis = new ProductHistory();
        prHis.setFlatMember(member);
        prHis.setProduct(product);
        prHis.setDate(new Date());
        productHistoryRepository.save(prHis);
    }

    @GetMapping("getProductHistory/{name}/{idUser}")
    public List<ProductHistoryDto> getProductHistory(@PathVariable String name,
                                                     @PathVariable Integer idUser){
        User user = userRepository.findUserById(idUser);
        FlatMember member = flatMemberRepository.findByUser(user).get(0);
        Product product = productRepository.findByName(name);
        List<ProductHistory> hisList = productHistoryRepository.findAllByProductAndFlat(product, member.getFlat());
        List<ProductHistoryDto> list = new ArrayList<>();
        int i=0;

        for(ProductHistory ph : hisList){
            if(i < 7) {
                ProductHistoryDto dto = new ProductHistoryDto();
                dto.setDate(ph.getDate());
                User u = userRepository.findByFlatMember(ph.getFlatMember());
                dto.setUserName(u.getName() + " " + u.getSurname());
                list.add(dto);
                i++;
            }
        }
        return list;

    }


    @GetMapping("registerBuying/{name}/{idUser}")
    public Boolean registerBuying(@PathVariable String name,@PathVariable Integer idUser) {
       ProductHistory productHistory = new ProductHistory();
       User user = userRepository.findUserById(idUser);
       FlatMember member = user.getFlatMemberList().get(0);
       productHistory.setFlatMember(member);
       productHistory.setDate(new Date());
       productHistory.setProduct(productRepository.findByName(name));
       productHistoryRepository.save(productHistory);
       return true;
    }
}


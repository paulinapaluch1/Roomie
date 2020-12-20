package com.pm.roomie.dao;

import com.pm.roomie.json.Flat;
import com.pm.roomie.json.FlatMember;


import com.pm.roomie.json.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatMemberRepository extends JpaRepository<FlatMember,Integer> {

    List<FlatMember> findByUser(User user);
    List<FlatMember> findByFlat(Flat flat);
}

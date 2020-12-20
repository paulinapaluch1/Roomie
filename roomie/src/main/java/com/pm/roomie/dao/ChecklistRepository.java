package com.pm.roomie.dao;


import com.pm.roomie.json.Checklist;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.Product;
import com.pm.roomie.json.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist,Integer> {

    @Query("SELECT name FROM Checklist b "
            + "WHERE b.flatMember = :member ")
    List<String> findByFlatMember(FlatMember member);

    @Query("SELECT b FROM Checklist b "
            + "WHERE b.name = :item " +
            "AND b.flatMember = :member")
    Checklist findByNameAndMember(String item, FlatMember member);
}

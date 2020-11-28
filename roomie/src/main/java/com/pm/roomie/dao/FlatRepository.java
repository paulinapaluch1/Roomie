package com.pm.roomie.dao;

import com.pm.roomie.json.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository<Flat,Long> {

    @Query("SELECT f FROM Flat f "  + "WHERE f.id = :id ")
    Flat findFlatById(Integer id);
    
}

package com.pm.roomie.dao;

import com.pm.roomie.json.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface BillTypeRepository extends JpaRepository<BillType,Long> {
        
    @Query("SELECT b FROM BillType b "  + "WHERE b.id = :id ")
    BillType findBillTypeById(Integer id);
    
}
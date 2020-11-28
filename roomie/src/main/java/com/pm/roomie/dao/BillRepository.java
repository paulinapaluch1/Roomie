package com.pm.roomie.dao;

import com.pm.roomie.json.Bill;
import com.pm.roomie.json.BillType;
import com.pm.roomie.json.Flat;
import com.pm.roomie.json.FlatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    
    List<Bill> findByFlat(Flat flat);
    
//    List<FlatMember> findFlatMembersByFlatId();
    
    @Query("SELECT b FROM Bill b "  + "WHERE b.id = :id ")
    Bill findBillById(Integer id);
    
    @Query("SELECT t FROM BillType t "  + "WHERE t.id = :id ")
    BillType findBillTypeById(Integer id);
    
}

package com.pm.roomie.dao;

import com.pm.roomie.json.MembersBill;
import com.pm.roomie.json.Bill;
import com.pm.roomie.json.FlatMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersBillRepository extends JpaRepository<MembersBill,Long>{
      
    @Query("SELECT b FROM MembersBill b " + "WHERE b.id = :id")
    MembersBill findMembersBillById(Integer id);
    
    List<MembersBill> findByFlatMember(FlatMember flatMember);
}
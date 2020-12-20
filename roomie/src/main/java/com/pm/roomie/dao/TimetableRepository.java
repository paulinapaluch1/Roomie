package com.pm.roomie.dao;

import com.pm.roomie.json.Bill;
import com.pm.roomie.json.BillType;
import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.Timetable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Long> {
    
    List<Timetable> findByFlatMember(FlatMember flatMember);

    @Query("SELECT t FROM Timetable t "  + "WHERE t.id = :id ")
    Timetable findTimetableById(Integer id);
    
    @Query("SELECT t FROM Timetable t "  + "WHERE t.flatMember.flat.id = :id ORDER BY t.date DESC")
    List<Timetable> findTimetableByFlat(Integer id);
    
}

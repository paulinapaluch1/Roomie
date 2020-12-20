package com.pm.roomie.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="timetable")
@Data
public class Timetable {
    
    @Id
    private int id;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore 
    @JoinColumn(name = "flatmember_id")
    private FlatMember flatMember;
    
    private String date;
    
}

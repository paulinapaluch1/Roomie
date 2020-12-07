package com.pm.roomie.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="membersbills")
@Data
public class MembersBill {
    
    @Id
    private int id;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore 
    @JoinColumn(name = "bills_id")
    private Bill bill;
    
    private float dividedamount;
    
    private boolean paid;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore
    @JoinColumn(name = "flatmembers_id")
    private FlatMember flatMember;
    
}

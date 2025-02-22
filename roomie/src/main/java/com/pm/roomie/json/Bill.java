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
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="bills")
@Data
public class Bill {
    @Id
    private int id;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore 
    @JoinColumn(name = "billstype_id")
    private BillType billType;
    
    private float amount;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore
    @JoinColumn(name = "flat_id")
    private Flat flat;
    
    @Column(name="date")
    private String billDate;
    
    private String comment;
    
    @OneToMany(mappedBy = "id")
    List<MembersBill> membersBillList;
    
}

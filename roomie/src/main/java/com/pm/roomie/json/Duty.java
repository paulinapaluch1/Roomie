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
@Table(name="duties")
@Data
public class Duty {
    
    @Id
    private int id;
    
    private String name;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore
    @JoinColumn(name = "rooms_id")
    private Room room;

    
}

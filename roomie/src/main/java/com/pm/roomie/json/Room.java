package com.pm.roomie.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="rooms")
@Data
public class Room {
    
    @Id
    private int id;
    
    private String name;
    
    @ManyToOne(cascade = { CascadeType.DETACH })
    @JsonIgnore
    @JoinColumn(name = "flats_id")
    private Flat flat;
    
    @OneToMany(mappedBy = "id")
    List<Duty> dutyList;

}

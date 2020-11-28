package com.pm.roomie.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="flats")
@Data
public class Flat {

    @Id
    private int id;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    List<FlatMember> flatMemberList;
    
    @OneToMany(mappedBy = "id")
    @JsonIgnore
    List<Bill> billList;
}

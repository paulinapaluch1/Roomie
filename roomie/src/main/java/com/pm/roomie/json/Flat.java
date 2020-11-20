package com.pm.roomie.json;

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
    List<FlatMember> flatMemberList;
}

package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="checklist")
@Data
public class Checklist {

    @Id
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "flatmember_id")
    private FlatMember flatMember;

    private String name;


}

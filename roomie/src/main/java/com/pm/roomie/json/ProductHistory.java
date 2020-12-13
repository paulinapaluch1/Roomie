package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="producthistory")
@Data
public class ProductHistory {

    @Id
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "flatmembers_id")
    private FlatMember flatMember;

    @Column(name="date")
    private Date date;


}

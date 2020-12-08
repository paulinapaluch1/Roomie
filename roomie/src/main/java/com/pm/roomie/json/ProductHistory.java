package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.*;

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
    @JoinColumn(name = "flatmember_id")
    private FlatMember flatMember;


}

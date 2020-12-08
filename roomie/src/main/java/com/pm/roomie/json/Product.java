package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    private int id;

    private String name;


}

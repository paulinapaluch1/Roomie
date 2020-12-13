package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "id")
    List<ProductHistory> productHistoryList;

}

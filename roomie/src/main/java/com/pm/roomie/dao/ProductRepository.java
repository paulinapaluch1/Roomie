package com.pm.roomie.dao;


import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT distinct b FROM Product b "
            + "JOIN ProductHistory h ON b.id = h.product "
            + "WHERE h.flatMember = :member ")
    List<Product> findByFlatMember(FlatMember member);

    Product findByName(String name);
}

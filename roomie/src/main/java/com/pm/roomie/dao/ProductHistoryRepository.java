package com.pm.roomie.dao;


import com.pm.roomie.json.Flat;
import com.pm.roomie.json.Product;
import com.pm.roomie.json.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Long> {

    @Query("SELECT h FROM ProductHistory h "
            + "JOIN FlatMember f ON f.id = h.flatMember "
            + "JOIN Flat fl ON fl.id = f.flat "
            + "WHERE fl = :flat and h.product = :product "  + "order by h.date desc")
    List<ProductHistory> findAllByProductAndFlat(Product product, Flat flat);

}

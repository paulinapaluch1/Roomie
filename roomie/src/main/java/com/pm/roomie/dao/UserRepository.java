package com.pm.roomie.dao;

import com.pm.roomie.json.FlatMember;
import com.pm.roomie.json.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findByLogin(String username);

    @Query("SELECT p FROM User p "  + "WHERE p.id = :id ")
    User findUserById(Integer id);


    @Query("SELECT h FROM User h "
            + "JOIN FlatMember f ON h.id = f.user "
            + "WHERE f = :member")
    User findByFlatMember(FlatMember member);
}

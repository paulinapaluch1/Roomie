package com.pm.roomie.json;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User {

   @Id
   private int id;

   private String name;

   private String surname;

   private boolean active;

   private String roles;

   private String login;

   private String password;

   private String phone;

   @OneToMany(mappedBy = "id")
   List<FlatMember> flatMemberList;

   @Override
   public String toString() {
      return "test";
   }
}

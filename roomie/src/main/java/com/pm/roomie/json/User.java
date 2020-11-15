package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="users")
@Data

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

}

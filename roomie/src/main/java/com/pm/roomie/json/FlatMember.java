package com.pm.roomie.json;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="flatmembers")
@Data
public class FlatMember {

   @Id
   private int id;

   @ManyToOne(cascade = { CascadeType.DETACH })
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne(cascade = { CascadeType.DETACH })
   @JoinColumn(name = "flat_id")
   private Flat flat;



}

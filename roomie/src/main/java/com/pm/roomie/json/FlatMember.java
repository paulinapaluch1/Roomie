package com.pm.roomie.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="flatmembers")
@Data
public class FlatMember {

   @Id
   private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "flat_id")
    @JsonIgnore
   private Flat flat;



}

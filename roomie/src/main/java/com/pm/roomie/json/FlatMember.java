package com.pm.roomie.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
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

   @ManyToOne(cascade = CascadeType.REMOVE)
   @JoinColumn(name = "flat_id")
    @JsonIgnore
    private Flat flat;

    @OneToMany(mappedBy = "id")
    List<MembersBill> membersBillList;

    @OneToMany(mappedBy = "id")
    List<ProductHistory> productHistoryList;

    @OneToMany(mappedBy = "id")
    List<Checklist> checklists;

}

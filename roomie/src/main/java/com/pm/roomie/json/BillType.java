package com.pm.roomie.json;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="billtypes")
@Data
public class BillType {
    
    @Id
    private int id;
    
    private String type;
    
    @OneToMany(mappedBy = "id")
    @JsonIgnore
    List<Bill> billList;
    
}

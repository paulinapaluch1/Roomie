package com.pm.roomie.dtos;

import lombok.Data;

@Data
public class FlatMemberObject {
    
    private int id;
    private String flatMemberName;
    private String flatMemberSurname;
    private int idFlat;
    private int idUser;
    
}
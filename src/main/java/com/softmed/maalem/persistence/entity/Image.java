package com.softmed.maalem.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;//UUID

    //private String extention;

    @ManyToOne
    private Service service;

}

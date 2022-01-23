package com.softmed.maalem.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Categorie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
}

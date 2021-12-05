package com.softmed.maalem.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Service implements Serializable {

    @Id
    private String id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private Coordonnee coordonnee;

    private String adresse;

    @ManyToOne
    private Categorie categorie;

    //@OneToMany
    //private Collection<Image> images;

    @ElementCollection
    @CollectionTable(name = "list_service", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "list")
    private List<String> list;

    @ManyToOne
    private Client client;

}

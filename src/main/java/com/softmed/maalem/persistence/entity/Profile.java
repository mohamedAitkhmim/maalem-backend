package com.softmed.maalem.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table(name = "profile", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cnie")
})
public class Profile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String phone;

    private Date dateInscription;

    private String cnie;

    @Column(columnDefinition = "TEXT")
    private String about;

    private String photo;

    private String background;

    private String facebook;

    private String linkedin;

    private String github;

    private String ville;

    @Column(columnDefinition = "TEXT")
    private String adresse;

}

package com.softmed.maalem.persistence.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Coordonnee implements Serializable {
    private Long latitude;
    private Long longitude;
}

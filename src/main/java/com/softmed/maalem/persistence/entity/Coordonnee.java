package com.softmed.maalem.persistence.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Coordonnee implements Serializable {
    private Long latitude;
    private Long longitude;
}

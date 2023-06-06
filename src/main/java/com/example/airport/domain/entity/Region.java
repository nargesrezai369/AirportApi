package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "region")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Region extends BaseEntity {

    private String code;
    private String localCode;
    private String name;
    private String continent;
    private String isoCountry;
    private String wikipediaLink;
    private String keywords;

}

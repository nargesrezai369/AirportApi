package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "countries")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country extends BaseEntity {

    private String code;

    private String name;

    private String continent;

    @Column(name = "wikipedia_link")
    private String wikipediaLink;

    private String keywords;

}

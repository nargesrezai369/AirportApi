package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "airport_frequencie")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AirportFrequencie extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "airport_ref")
    private Airport airportRef;

    private String airportIdent;
    private String type;
    private String description;
    private String frequencyMhz;

}

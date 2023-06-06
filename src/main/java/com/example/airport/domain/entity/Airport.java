package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "airports")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Airport extends BaseEntity {

    private String ident;

    private String type;

    private String name;

    @Column(name = "latitude_deg")
    private String latitudeDeg;

    @Column(name = "longitude_deg")
    private String longitudeDeg;

    @Column(name = "elevation_ft")
    private String elevationFt;

    private String continent;

    @Column(name = "iso_region")
    private String isoRegion;

    private String municipality;

    @Column(name = "scheduled_service")
    private String scheduledService;

    @Column(name = "gps_code")
    private String gpsCode;

    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "local_code")
    private String localCode;

    @Column(name = "home_link")
    private String homeLink;

    @Column(name = "wikipedia_link")
    private String wikipediaLink;

    private String keywords;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "airportRef")
    private Set<Runway> runwayList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "airportRef")
    private Set<AirportFrequencie> airportFrequencieList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "associatedAirport")
    private Set<Navaid> navaidList;

    @ManyToOne
    @JoinColumn(name = "iso_country", referencedColumnName = "code")
    private Country country;

}

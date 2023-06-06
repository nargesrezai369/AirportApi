package com.example.airport.application.models;

import lombok.*;

import java.util.Set;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AirportModel {

    private String ident;
    private String type;
    private String name;
    private String latitudeDeg;
    private String longitudeDeg;
    private String elevationFt;
    private String continent;
    private String isoCountry;
    private String isoRegion;
    private String municipality;
    private String scheduledService;
    private String gpsCode;
    private String iataCode;
    private String localCode;
    private String homeLink;
    private String wikipediaLink;
    private String keywords;
    private String countryCode;

    private Set<RunwayModel> runwayList;

    private Set<AirportFrequencieModel> airportFrequencieList;

    private Set<NavaidModel> navaidList;

}

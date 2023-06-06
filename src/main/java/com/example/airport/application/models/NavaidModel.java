package com.example.airport.application.models;

import lombok.*;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NavaidModel {

    private String filename;
    private String ident;
    private String name;
    private String type;
    private String frequencyKhz;
    private String latitudeDeg;
    private String longitudeDeg;
    private String elevationFt;
    private String isoCountry;
    private String dmeFrequencyKhz;
    private String dmeChannel;
    private String dmeLatitudeDeg;
    private String dmeLongitudeDeg;
    private String dmeElevationFt;
    private String slavedVariationDeg;
    private String magneticVariationDeg;
    private String usageType;
    private String power;

}

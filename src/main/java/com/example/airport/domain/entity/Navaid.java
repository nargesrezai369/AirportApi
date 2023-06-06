package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "navaids")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Navaid extends BaseEntity {

    private String filename;

    private String ident;

    private String name;

    private String type;

    @Column(name = "frequency_khz")
    private String frequencyKhz;

    @Column(name = "latitude_deg")
    private String latitudeDeg;

    @Column(name = "longitude_deg")
    private String longitudeDeg;

    @Column(name = "elevation_ft")
    private String elevationFt;

    @Column(name = "iso_country")
    private String isoCountry;

    @Column(name = "dme_frequency_khz")
    private String dmeFrequencyKhz;

    @Column(name = "dme_channel")
    private String dmeChannel;

    @Column(name = "dme_latitude_deg")
    private String dmeLatitudeDeg;

    @Column(name = "dme_longitude_deg")
    private String dmeLongitudeDeg;

    @Column(name = "dme_elevation_ft")
    private String dmeElevationFt;

    @Column(name = "slaved_variation_deg")
    private String slavedVariationDeg;

    @Column(name = "magnetic_variation_deg")
    private String magneticVariationDeg;

    private String usageType;

    private String power;

    @ManyToOne
    @JoinColumn(name = "associated_airport", referencedColumnName = "ident")
    private Airport associatedAirport;
}

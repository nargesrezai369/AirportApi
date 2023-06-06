package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "runways")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Runway extends BaseEntity {

    @Column(name = "airport_ident")
    private String airportIdent;

    @Column(name = "length_ft")
    private Double lengthFt;

    @Column(name = "width_ft")
    private Double widthFt;

    @Column(name = "surface")
    private String surface;

    @Column(name = "lighted")
    private String lighted;

    @Column(name = "closed")
    private String closed;

    @Column(name = "le_ident")
    private String leIdent;

    @Column(name = "le_latitude_deg")
    private String leLatitudeDeg;

    @Column(name = "le_longitude_deg")
    private String leLongitudeDeg;

    @Column(name = "le_elevation_ft")
    private String leElevationFt;

    @Column(name = "le_heading_degT")
    private Double leHeadingDegT;

    @Column(name = "le_displaced_threshold_ft")
    private String leDisplacedThresholdFt;

    @Column(name = "he_ident")
    private String heIdent;

    @Column(name = "he_latitude_deg")
    private String heLatitudeDeg;

    @Column(name = "he_longitude_deg")
    private String heLongitudeDeg;

    @Column(name = "he_elevation_ft")
    private String heElevationFt;

    @Column(name = "he_heading_degT")
    private String heHeadingDegT;

    @Column(name = "he_displaced_threshold_ft")
    private String heDisplacedThresholdFt;

    @ManyToOne
    @JoinColumn(name = "airport_ref")
    private Airport airportRef;

}

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
public class RunwayModel {

    private Long id;
    private String airportIdent;
    private Double lengthFt;
    private Double widthFt;
    private String surface;
    private String lighted;
    private String closed;
    private String leIdent;
    private String leLatitudeDeg;
    private String leLongitudeDeg;
    private String leElevationFt;
    private Double leHeadingDegT;
    private String leDisplacedThresholdFt;
    private String heIdent;
    private String heLatitudeDeg;
    private String heLongitudeDeg;
    private String heElevationFt;
    private String heHeadingDegT;
    private String heDisplacedThresholdFt;

}

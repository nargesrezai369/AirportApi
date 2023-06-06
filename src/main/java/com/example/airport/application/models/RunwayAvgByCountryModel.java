package com.example.airport.application.models;

import lombok.*;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RunwayAvgByCountryModel {

    private String countryCode;
    private String countryName;
    private double average;

}

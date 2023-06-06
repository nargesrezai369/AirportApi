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
public class AirportFrequencieModel {

    private String airportIdent;
    private String type;
    private String description;
    private String frequencyMhz;

}

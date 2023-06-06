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
public class CountryModel {

    private String name;
    private int airportCount;

}

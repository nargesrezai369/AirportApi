package com.example.airport.application;

import com.example.airport.application.models.AirportByCountryModel;
import com.example.airport.application.models.AirportModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
public interface AirportService {

    Optional<AirportModel> getAirportByIdent(String id);

    Page<AirportByCountryModel> getAirportByCountry(String searchKey, int page, int size);

}

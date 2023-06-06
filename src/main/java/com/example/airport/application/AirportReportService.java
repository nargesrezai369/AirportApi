package com.example.airport.application;

import com.example.airport.application.models.AirportsCountByTypeAndCountryModel;
import com.example.airport.application.models.CountryModel;
import com.example.airport.application.models.RunwayAvgByCountryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
public interface AirportReportService {

    List<CountryModel> getCountryWithHighestActiveAirports(Sort.Direction sortType);

    Page<AirportsCountByTypeAndCountryModel> findAirportsCountByTypeAndCountry
            (int page, int size, Sort.Direction sortType);

    Page<RunwayAvgByCountryModel> findRunwayAverageByCountry
            (Double minValue, Double maxValue, int page, int size, Sort.Direction sortType);

}

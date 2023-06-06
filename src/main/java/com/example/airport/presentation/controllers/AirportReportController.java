package com.example.airport.presentation.controllers;

import com.example.airport.application.AirportReportService;
import com.example.airport.application.models.AirportsCountByTypeAndCountryModel;
import com.example.airport.application.models.CountryModel;
import com.example.airport.application.models.RunwayAvgByCountryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * These ARIs are for Airport Resports.
 *
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@RestController
@RequestMapping("/api/airport/reports")
@RequiredArgsConstructor
public class AirportReportController {

    private final AirportReportService airportReportService;

    /**
     * 10 countries with highest number of active/open airports (with count) and countries and with lowest number
     * of active/open airports (also 10). It depends on sortType.
     *
     * @param sortType
     * @return List<CountryModel>
     */
    @GetMapping("/active-airport")
    public ResponseEntity<List<CountryModel>> getCountryWithHighestActiveAirports(
            @RequestParam @NotNull Sort.Direction sortType) {
        List<CountryModel> countryModelList = airportReportService.getCountryWithHighestActiveAirports(sortType);
        return ResponseEntity.ok(countryModelList);
    }

    /**
     * Report with the number of airports grouped by type and country.
     *
     * @param page
     * @param size
     * @param sortType
     * @return Page<AirportsCountByTypeAndCountryModel>
     */
    @GetMapping("/count-type-country")
    public ResponseEntity<Page<AirportsCountByTypeAndCountryModel>> findAirportsCountByTypeAndCountry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortType
    ) {
        Page<AirportsCountByTypeAndCountryModel> countryModelList =
                airportReportService.findAirportsCountByTypeAndCountry(page, size, sortType);
        return ResponseEntity.ok(countryModelList);
    }

    /**
     * Report with the average length of the runway with a runway heading between minValue and maxValue per country or
     * all value without determine rang.
     *
     * @param page
     * @param size
     * @param sortType
     * @param minValue
     * @param maxValue
     * @return
     */
    @GetMapping("/average-runway-country")
    public ResponseEntity<Page<RunwayAvgByCountryModel>> findRunwayAverageByCountry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortType,
            @RequestParam(required = false) Double minValue,
            @RequestParam(required = false) Double maxValue
    ) {
        Page<RunwayAvgByCountryModel> countryModelList =
                airportReportService.findRunwayAverageByCountry(minValue, maxValue, page, size, sortType);
        return ResponseEntity.ok(countryModelList);
    }

}

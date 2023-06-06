package com.example.airport.presentation.controllers;

import com.example.airport.application.AirportService;
import com.example.airport.application.models.AirportByCountryModel;
import com.example.airport.application.models.AirportModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * These APIs are for Queries
 *
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    /**
     * get the airport information by ident
     *
     * @param ident
     * @return ResponseEntity<AirportModel>
     */
    @GetMapping("/byIdent/{ident}")
    public ResponseEntity<AirportModel> getAirportByIdent(@PathVariable("ident") @NotNull String ident) {

        Optional<AirportModel> airportModelOptional = airportService.getAirportByIdent(ident);
        return airportModelOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This method check the searchKey pattern to find it's a country code or name.
     * If it's a country code then return true.
     *
     * @param page
     * @param size
     * @param searchKey
     * @return
     */
    @GetMapping("/byCountry")
    public ResponseEntity<Page<AirportByCountryModel>> getAirportByCountry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "US") String searchKey
    ) {

        Page<AirportByCountryModel> airportModelOptional = airportService.getAirportByCountry(searchKey, page, size);
        return ResponseEntity.ok(airportModelOptional);
    }

}

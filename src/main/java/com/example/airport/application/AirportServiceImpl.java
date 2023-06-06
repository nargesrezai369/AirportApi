package com.example.airport.application;

import com.example.airport.application.models.AirportByCountryModel;
import com.example.airport.application.models.AirportModel;
import com.example.airport.domain.entity.Airport;
import com.example.airport.domain.entity.Country;
import com.example.airport.domain.repository.AirportRepository;
import com.example.airport.domain.repository.CountryRepository;
import com.example.airport.exceptions.EntityNotFoundException;
import com.example.airport.mappers.AirportMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CountryRepository countryRepository;
    private final AirportMapper airportMapper;

    /**
     * Query the airport information.
     *
     * @param ident
     * @return Optional<AirportModel> that is airport, navaid, runways, frequencies.
     */
    @Override
    @Transactional
    public Optional<AirportModel> getAirportByIdent(String ident) {

        log.info("get airport by ident.");

        Optional<Airport> airportOptional = airportRepository.findByIdent(ident);

        return airportOptional.map(airport -> {
            return Optional.of(airportMapper.mapTo(airport));
        }).orElse(Optional.empty());

    }

    /**
     * Query all airports within a country. . The input can be the country code (e.g.: NL, BE) or fullname
     * (e.g.: Netherlands, Belgium).
     *
     * @param searchKey
     * @param page
     * @param size
     * @return Page<AirportByCountryModel>
     */
    @Override
    public Page<AirportByCountryModel> getAirportByCountry(String searchKey, int page, int size) {

        log.info("get airport by country.");

        String code = (isCountryCode(searchKey) ? searchKey : findCountryCode(searchKey));

        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());

        Page<AirportByCountryModel> airportByCounterModelPage =
                airportRepository.findByCountryCode(code, pageable).map(airportMapper::map);

        log.info("total airport by country is {}.", airportByCounterModelPage.getTotalElements());

        return airportByCounterModelPage;
    }

    /**
     * This method check the searchKey pattern to find it's a country code or name.
     * If it's a country code then return true.
     *
     * @param searchKey
     * @return boolean
     */
    private boolean isCountryCode(String searchKey) {

        return searchKey.matches("^[A-Z]{2}$");

    }

    /**
     * This method find country in database base on its name.
     * If country doesn't exist then it throws the EntityNotFoundException.
     *
     * @param searchKey
     * @return
     */
    private String findCountryCode(String searchKey) {

        Optional<Country> countryOptional = countryRepository.findByName(searchKey);

        return countryOptional.map(country -> {
            return country.getCode();
        }).orElseThrow(() -> new EntityNotFoundException("There is not country with " + searchKey + " name."));
    }

}

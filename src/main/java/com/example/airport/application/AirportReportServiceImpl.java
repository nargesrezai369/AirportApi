package com.example.airport.application;

import com.example.airport.application.models.AirportsCountByTypeAndCountryModel;
import com.example.airport.application.models.CountryModel;
import com.example.airport.application.models.RunwayAvgByCountryModel;
import com.example.airport.domain.repository.AirportRepository;
import com.example.airport.domain.repository.CountryRepository;
import com.example.airport.mappers.AirportMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AirportReportServiceImpl implements AirportReportService {

    private final CountryRepository countryRepository;
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    /**
     * 10 countries with highest number of active/open airports (with count) and countries and with lowest number
     * of active/open airports (also 10). It depends on sortType.
     *
     * @param sortType :ASC or DESC
     * @return List<CountryModel>
     */
    @Override
    public List<CountryModel> getCountryWithHighestActiveAirports(Sort.Direction sortType) {

        log.info("get country with highest active airports.");

        Pageable pageable = PageRequest.of(0, 10, Sort.by(sortType, "airportCount"));

        Page<Object[]> result = countryRepository.findTopCountriesWithHighestAirportCount(pageable);

        return result.stream()
                .map(airportMapper::mapObjectToCountryModel)
                .collect(Collectors.toList());

    }

    /**
     * Report with the number of airports grouped by type and country.
     *
     * @param page
     * @param size
     * @param sortType base on airportCount
     * @return List<AirportsCountByTypeAndCountryModel>
     */
    @Override
    public Page<AirportsCountByTypeAndCountryModel> findAirportsCountByTypeAndCountry(int page, int size,
                                                                                      Sort.Direction sortType) {

        log.info("find airports count by type and country.");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType, "airportCount"));

        Page<Object[]> objects = airportRepository.findAirportsCountByTypeAndCountry(pageable);

        return objects.map(airportMapper::mapObjectToCounterModel);

    }

    /**
     * Report with the average length of the runway with a runway heading between minValue and maxValue per country or
     * all value without determine rang.
     *
     * @param minValue
     * @param maxValue
     * @param page
     * @param size
     * @param sortType
     * @return List<RunwayAvgByCountryModel>
     */
    @Override
    public Page<RunwayAvgByCountryModel> findRunwayAverageByCountry(Double minValue, Double maxValue,
                                                                    int page, int size, Sort.Direction sortType) {

        log.info("find runways average by country.");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType, "average"));

        Page<Object[]> objects = airportRepository.getAverageRunwayLengthPerCountry
                (minValue, maxValue, pageable);

        return objects.map(airportMapper::mapObjectToRunwayAvgByCountryModel);

    }
}
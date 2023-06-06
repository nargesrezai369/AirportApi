package com.example.airport.util;

import com.example.airport.domain.entity.Airport;
import com.example.airport.domain.entity.Country;
import com.example.airport.domain.entity.Runway;
import com.example.airport.domain.repository.AirportRepository;
import com.example.airport.domain.repository.CountryRepository;
import com.example.airport.domain.repository.RunwaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Component
public class TestUtil {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RunwaysRepository runwaysRepository;

    @PostConstruct
    public void createAirport() {
        //
        Country countryNL = Country.builder()
                .code("NL")
                .name("Netherlands")
                .build();
        Country countryUS = Country.builder()
                .code("US")
                .name("United State")
                .build();
        countryRepository.save(countryUS);
        countryRepository.save(countryNL);
        //
        for (int airportCounter = 0; airportCounter < 6; airportCounter++) {
            //
            Airport airport01 = Airport.builder()
                    .name("AirportNameOne" + airportCounter)
                    .ident("AirportIdentOne" + airportCounter)
                    .type("closed")
                    .country(countryNL).build();
            airportRepository.save(airport01);
            Runway runway01 = Runway.builder()
                    .airportRef(airport01)
                    .airportIdent(airport01.getIdent())
                    .lengthFt((double) (100))
                    .leHeadingDegT((double) (100))
                    .build();
            runwaysRepository.save(runway01);
            Runway runway02 = Runway.builder()
                    .airportIdent(airport01.getIdent())
                    .leHeadingDegT((double) (180))
                    .airportRef(airport01)
                    .lengthFt((double) (180)).build();
            runwaysRepository.save(runway02);

            Airport airport1 = Airport.builder()
                    .name("AirportNameTwo" + airportCounter)
                    .ident("AirportIdentTwo" + airportCounter)
                    .type("heliport")
                    .country(countryNL).build();
            airportRepository.save(airport1);
            Runway runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (160))
                    .airportRef(airport1)
                    .lengthFt((double) (130)).build();
            runwaysRepository.save(runway1);
            runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (100))
                    .airportRef(airport1)
                    .lengthFt((double) (100)).build();
            runwaysRepository.save(runway1);
            airport1 = Airport.builder()
                    .name("AirportNameThree" + airportCounter)
                    .ident("AirportIdentThree" + airportCounter)
                    .type("heliport")
                    .country(countryNL).build();
            airportRepository.save(airport1);
            runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (160))
                    .airportRef(airport1)
                    .lengthFt((double) (160)).build();
            runwaysRepository.save(runway1);
            runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (180))
                    .airportRef(airport1)
                    .lengthFt((double) (177)).build();
            runwaysRepository.save(runway1);

        }

        //
        for (int airportCounter = 0; airportCounter < 4; airportCounter++) {
            //
            Airport airport1 = Airport.builder()
                    .name("AirportNameFour" + airportCounter)
                    .ident("AirportIdentFour" + airportCounter)
                    .type("closed")
                    .country(countryUS).build();
            airportRepository.save(airport1);
            Runway runway1 = Runway.builder()
                    .airportRef(airport1)
                    .airportIdent(airport1.getIdent())
                    .lengthFt((double) (181))
                    .leHeadingDegT((double) (183))
                    .build();
            runwaysRepository.save(runway1);
            runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (182))
                    .airportRef(airport1)
                    .lengthFt((double) (178)).build();
            runwaysRepository.save(runway1);
            airport1 = Airport.builder()
                    .name("AirportNameFive" + airportCounter)
                    .ident("AirportIdentFive" + airportCounter)
                    .type("heliport")
                    .country(countryUS).build();
            airportRepository.save(airport1);
            runway1 = Runway.builder()
                    .airportRef(airport1)
                    .airportIdent(airport1.getIdent())
                    .lengthFt((double) (150))
                    .leHeadingDegT((double) (173))
                    .build();
            runwaysRepository.save(runway1);
            runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (184))
                    .airportRef(airport1)
                    .lengthFt((double) (184)).build();
            runwaysRepository.save(runway1);
            airport1 = Airport.builder()
                    .name("AirportNameSix" + airportCounter)
                    .ident("AirportIdentSix" + airportCounter)
                    .type("heliport")
                    .country(countryUS).build();
            airportRepository.save(airport1);
            runway1 = Runway.builder()
                    .airportRef(airport1)
                    .airportIdent(airport1.getIdent())
                    .lengthFt((double) (180))
                    .leHeadingDegT((double) (180))
                    .build();
            runwaysRepository.save(runway1);
            runway1 = Runway.builder()
                    .airportIdent(airport1.getIdent())
                    .leHeadingDegT((double) (184))
                    .airportRef(airport1)
                    .lengthFt((double) (184)).build();
            runwaysRepository.save(runway1);

        }

    }
}

package com.example.airport.domain;

import com.example.airport.AirportApplication;
import com.example.airport.domain.entity.Airport;
import com.example.airport.domain.repository.AirportRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@SpringBootTest(classes = AirportApplication.class)
public class AirportRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;

    @Test
    @Description("Airport Ident is not valid.")
    public void getAirportByIdentInvalidIdTest() {

        //************************
        //          Given
        //************************
        String sampleIdent = "xxx";

        //************************
        //          Initialize
        //************************
        getAirportByIdentInvalidIdTestInit(sampleIdent);

        //************************
        //          WHEN
        //************************
        Optional<Airport> result = airportRepository.findByIdent(sampleIdent);

        //************************
        //          THEN
        //************************
        assertThat(result).isEmpty();

    }

    private void getAirportByIdentInvalidIdTestInit(String sampleId) {
        Optional<Airport> airportOptional = airportRepository.findByIdent(sampleId);
        if (airportOptional.isPresent())
            airportRepository.delete(airportOptional.get());
    }

    @Test
    @Description("Airport Ident is  valid.")
    public void getAirportByIdentValidIdTest() {

        //************************
        //          Given
        //************************
        String sampleIdent = "AirportIdentOne0";

        //************************
        //          WHEN
        //************************
        Optional<Airport> result = airportRepository.findByIdent(sampleIdent);

        //************************
        //          THEN
        //************************
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("AirportNameOne0");

    }
}

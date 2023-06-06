package com.example.airport.application;

import com.example.airport.AirportApplication;
import com.example.airport.application.models.AirportByCountryModel;
import com.example.airport.application.models.AirportModel;
import com.example.airport.domain.entity.Airport;
import com.example.airport.domain.repository.AirportRepository;
import com.example.airport.exceptions.EntityNotFoundException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@SpringBootTest(classes = AirportApplication.class)
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    @Description("Airport Ident is not valid.")
    public void getAirportByIdentInvalidIdTest() throws Exception {

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
        Optional<AirportModel> result = airportService.getAirportByIdent(sampleIdent);

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
    @Description("Airport Id is valid.")
    public void getAirportByIdentValidIdTest() throws Exception {

        //************************
        //          Given
        //************************
        String sampleIdent = "AirportIdentOne0";

        //************************
        //          WHEN
        //************************
        Optional<AirportModel> result = airportService.getAirportByIdent(sampleIdent);

        //************************
        //          THEN
        //************************
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("AirportNameOne0");

    }

    @Test
    @Description("country code is valid.")
    public void getAirportByCountryTestValidCode() throws Exception {

        //************************
        //          WHEN
        //************************
        Page<AirportByCountryModel> airportByCountryModelPage = airportService.getAirportByCountry
                ("US", 0, 10);

        //************************
        //          THEN
        //************************
        assertThat(airportByCountryModelPage.getNumberOfElements()).isEqualTo(10);
        assertThat(airportByCountryModelPage.getTotalElements()).isEqualTo(12);
        assertThat(airportByCountryModelPage.getContent().get(0).getCountryCode()).isEqualTo("US");

    }

    @Test
    @Description("country code is not valid.")
    public void getAirportByCountryTestInValidCode() throws Exception {

        //************************
        //          WHEN
        //************************
        assertThrows(EntityNotFoundException.class, () -> {
            airportService.getAirportByCountry("00", 0, 10);
        });
    }

    @Test
    @Description("country name is valid.")
    public void getAirportByCountryTestValidCountryName() throws Exception {

        //************************
        //          WHEN
        //************************
        Page<AirportByCountryModel> airportByCountryModelPage = airportService.getAirportByCountry
                ("United State", 0, 10);

        //************************
        //          THEN
        //************************
        assertThat(airportByCountryModelPage.getNumberOfElements()).isEqualTo(10);
        assertThat(airportByCountryModelPage.getTotalElements()).isEqualTo(12);
        assertThat(airportByCountryModelPage.getContent().get(0).getCountryCode()).isEqualTo("US");

    }

    @Test
    @Description("country name is not valid.")
    public void getAirportByCountryTestInValidCountryName() throws Exception {

        //************************
        //          WHEN
        //************************
        assertThrows(EntityNotFoundException.class, () -> {
            airportService.getAirportByCountry("INVALID", 0, 10);
        });
    }

}

package com.example.airport.presentation;

import com.example.airport.AirportApplication;
import com.example.airport.application.AirportService;
import com.example.airport.application.models.AirportByCountryModel;
import com.example.airport.application.models.AirportModel;
import com.example.airport.domain.entity.Airport;
import com.example.airport.domain.repository.AirportRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@SpringBootTest(classes = AirportApplication.class)
@AutoConfigureMockMvc
public class AirportControllerTest {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Description("Airport Id is null.")
    public void getAirportByIdentNullIdTest() throws Exception {

        //************************
        //          WHEN
        //************************
        mockMvc.perform(get("/api/airport/byIdent/"))
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    @Description("Airport Id is not valid.")
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
        mockMvc.perform(get("/api/airport/byIdent/{ident}", sampleIdent))
                .andExpect(status().isNotFound())
                .andReturn();

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
        MvcResult result = mockMvc.perform(get("/api/airport/byIdent/{ident}", sampleIdent)
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();
        assertThat(response).isNotBlank();

        ObjectMapper objectMapper = new ObjectMapper();
        AirportModel airportModel = objectMapper.readValue(response, AirportModel.class);
        assertThat(airportModel.getName()).isEqualTo("AirportNameOne0");
        assertThat(airportModel.getRunwayList().size()).isEqualTo(2);

    }

    @Test
    @Description("Test default value in params.")
    public void getAirportByCountryTestDefaultValue() throws Exception {

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(get("/api/airport/byCountry")
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();
        assertThat(response).isNotBlank();

        JSONObject airportModelJsonObject = new JSONObject(response);
        List<AirportByCountryModel> airportByCountryModelList = getAirportByCountryModelList(airportModelJsonObject);

        assertThat(airportModelJsonObject.get("numberOfElements")).isEqualTo(10);
        assertThat(airportModelJsonObject.get("totalElements")).isEqualTo(12);
        assertThat(airportByCountryModelList.get(0).getCountryCode()).isEqualTo("US");

    }

    @Test
    @Description("country code is valid.")
    public void getAirportByCountryTestValidCode() throws Exception {

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(get("/api/airport/byCountry")
                .param("page", "0")
                .param("size", "10")
                .param("searchKey", "US")
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();
        assertThat(response).isNotBlank();

        JSONObject airportModelJsonObject = new JSONObject(response);
        List<AirportByCountryModel> airportByCountryModelList = getAirportByCountryModelList(airportModelJsonObject);

        assertThat(airportModelJsonObject.get("numberOfElements")).isEqualTo(10);
        assertThat(airportModelJsonObject.get("totalElements")).isEqualTo(12);
        assertThat(airportByCountryModelList.get(0).getCountryCode()).isEqualTo("US");

    }

    @Test
    @Description("Country name is valid.")
    public void getAirportByCountryTestValidCountryName() throws Exception {

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(get("/api/airport/byCountry")
                .param("page", "0")
                .param("size", "10")
                .param("searchKey", "United State")
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        String response = result.getResponse().getContentAsString();
        assertThat(response).isNotBlank();

        JSONObject airportModelJsonObject = new JSONObject(response);
        List<AirportByCountryModel> airportByCountryModelList = getAirportByCountryModelList(airportModelJsonObject);

        assertThat(airportModelJsonObject.get("numberOfElements")).isEqualTo(10);
        assertThat(airportModelJsonObject.get("totalElements")).isEqualTo(12);
        assertThat(airportByCountryModelList.get(0).getCountryCode()).isEqualTo("US");

    }

    private List<AirportByCountryModel> getAirportByCountryModelList(
            JSONObject airportModelJsonObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AirportByCountryModel> airportByCountryModelList = objectMapper.readValue(airportModelJsonObject
                .get("content").toString(), new TypeReference<List<AirportByCountryModel>>() {
        });
        return airportByCountryModelList;
    }

    @Test
    @Description("Country name is not Invalid")
    public void getAirportByCountryTestInvalidCountryName() throws Exception {

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(get("/api/airport/byCountry")
                .param("page", "0")
                .param("size", "10")
                .param("searchKey", "Invalid Country")
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    @Description("Country code is not Invalid")
    public void getAirportByCountryTestInvalidCountryCode() throws Exception {

        //************************
        //          WHEN
        //************************
        MvcResult result = mockMvc.perform(get("/api/airport/byCountry")
                .param("page", "0")
                .param("size", "10")
                .param("searchKey", "00")
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();

    }

}

package com.example.airport.presentation;

import com.example.airport.AirportApplication;
import com.example.airport.application.AirportReportService;
import com.example.airport.application.models.AirportByCountryModel;
import com.example.airport.application.models.AirportsCountByTypeAndCountryModel;
import com.example.airport.application.models.RunwayAvgByCountryModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@SpringBootTest(classes = AirportApplication.class)
@AutoConfigureMockMvc
public class AirportReportControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AirportReportService airportReportService;

    @Test
    @Description("Test retuns ok .10 countries with highest number of active/open airports")
    public void getCountryWithHighestActiveAirportsTest() throws Exception {

        //************************
        //          Given
        //************************
        Sort.Direction sortType = Sort.Direction.DESC;

        //************************
        //          WHEN
        //************************

        mockMvc.perform(get("/api/airport/reports/active-airport")
                .param("sortType", sortType.name())
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].airportCount").value(12))
                .andExpect(jsonPath("$[1].airportCount").value(8))
                .andExpect(jsonPath("$.length()").value(Matchers.lessThan(11)))
                .andReturn();

    }

    @Test
    @Description("Test retuns ok .10 countries with lowest number of active/open airports")
    public void getCountryWithLowestActiveAirportsTest() throws Exception {

        //************************
        //          Given
        //************************
        Sort.Direction sortType = Sort.Direction.ASC;

        //************************
        //          WHEN
        //************************

        mockMvc.perform(get("/api/airport/reports/active-airport")
                .param("sortType", sortType.name())
                .contentType(MediaType.ALL_VALUE)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].airportCount").value(8))
                .andExpect(jsonPath("$[1].airportCount").value(12))
                .andExpect(jsonPath("$.length()").value(Matchers.lessThan(11)))
                .andReturn();

    }

    @Test
    @Description("Report with the number of airports grouped by type and country.")
    public void findAirportsCountByTypeAndCountryTest() throws Exception {

        //************************
        //          Given
        //************************
        Sort.Direction sortType = Sort.Direction.ASC;

        //************************
        //          WHEN
        //************************

        MvcResult result = mockMvc.perform(get("/api/airport/reports/count-type-country")
                .param("sortType", sortType.name())
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
        AirportsCountByTypeAndCountryModel airportsCountByTypeAndCountryModel =
                getAirportsCountByTypeAndCountryModelList(airportModelJsonObject).get(0);

        assertThat(airportModelJsonObject.get("numberOfElements")).isEqualTo(4);
        assertThat(airportModelJsonObject.get("totalElements")).isEqualTo(4);
        assertThat(airportsCountByTypeAndCountryModel.getCode()).isEqualTo("US");
        assertThat(airportsCountByTypeAndCountryModel.getType()).isEqualTo("closed");
        assertThat(airportsCountByTypeAndCountryModel.getCount()).isEqualTo(4);

    }

    private List<AirportsCountByTypeAndCountryModel> getAirportsCountByTypeAndCountryModelList(
            JSONObject airportModelJsonObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AirportsCountByTypeAndCountryModel> airportByCountryModelList = objectMapper.readValue(airportModelJsonObject
                .get("content").toString(), new TypeReference<List<AirportsCountByTypeAndCountryModel>>() {
        });
        return airportByCountryModelList;
    }

    @Test
    @Description("Report with the average length of the runway.")
    public void findRunwayAverageByCountryOKTest() throws Exception {

        //************************
        //          WHEN
        //************************

        MvcResult result = mockMvc.perform(get("/api/airport/reports/average-runway-country")
                .param("minValue",  (String) null)
                .param("maxValue",  (String) null)
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
        RunwayAvgByCountryModel runwayAvgByCountryModel =
                getRunwayAvgByCountryModelList(airportModelJsonObject).get(0);

        assertThat(airportModelJsonObject.get("numberOfElements")).isEqualTo(2);
        assertThat(airportModelJsonObject.get("totalElements")).isEqualTo(2);
        assertThat(runwayAvgByCountryModel.getAverage()).isEqualTo(141.16666666666666);

    }

    @Test
    @Description("Report with the average length of the runway with a runway heading between 175 and 185 per country.")
    public void findRunwayAverageByCountryWithLimitOKTest() throws Exception {

        //************************
        //          WHEN
        //************************

        MvcResult result = mockMvc.perform(get("/api/airport/reports/average-runway-country")
                .param("minValue", "175")
                .param("maxValue", "185")
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
        RunwayAvgByCountryModel runwayAvgByCountryModel =
                getRunwayAvgByCountryModelList(airportModelJsonObject).get(0);

        assertThat(airportModelJsonObject.get("numberOfElements")).isEqualTo(2);
        assertThat(airportModelJsonObject.get("totalElements")).isEqualTo(2);
        assertThat(runwayAvgByCountryModel.getAverage()).isEqualTo(178.5);

    }

    private List<RunwayAvgByCountryModel> getRunwayAvgByCountryModelList(
            JSONObject airportModelJsonObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RunwayAvgByCountryModel> airportByCountryModelList = objectMapper.readValue(airportModelJsonObject
                .get("content").toString(), new TypeReference<List<RunwayAvgByCountryModel>>() {
        });
        return airportByCountryModelList;
    }
}

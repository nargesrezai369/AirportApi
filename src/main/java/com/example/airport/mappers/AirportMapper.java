package com.example.airport.mappers;

import com.example.airport.application.models.*;
import com.example.airport.domain.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(target = "countryCode", source = "country.code")
    AirportModel mapTo(Airport model);

    @Mapping(target = "countryCode", source = "country.code")
    AirportByCountryModel map(Airport model);

    default CountryModel mapObjectToCountryModel(Object[] object) {
        CountryModel countryModel = new CountryModel();
        countryModel.setName((String) object[0]);
        countryModel.setAirportCount(Math.toIntExact((Long) object[1]));
        return countryModel;
    }

    default AirportsCountByTypeAndCountryModel mapObjectToCounterModel(Object[] object) {
        AirportsCountByTypeAndCountryModel countryModel = new AirportsCountByTypeAndCountryModel();
        countryModel.setType((String) object[0]);
        countryModel.setCode((String) object[1]);
        countryModel.setCount(Math.toIntExact((Long) object[2]));
        return countryModel;
    }

    default RunwayAvgByCountryModel mapObjectToRunwayAvgByCountryModel(Object[] object) {
        RunwayAvgByCountryModel countryModel = new RunwayAvgByCountryModel();
        countryModel.setCountryCode((String) object[0]);
        countryModel.setCountryName((String) object[1]);
        countryModel.setAverage(((Double) object[2]));
        return countryModel;
    }

}

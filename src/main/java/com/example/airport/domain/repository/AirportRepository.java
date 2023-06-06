package com.example.airport.domain.repository;

import com.example.airport.domain.entity.Airport;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByIdent(String ident);

    Page<Airport> findByCountryCode(String isoCountry, Pageable pageable);

    @Query("SELECT a.type, a.country.code, COUNT(a) AS airportCount FROM Airport a GROUP BY a.type, a.country.code")
    Page<Object[]> findAirportsCountByTypeAndCountry(Pageable pageable);


    @Query("SELECT a.country.code, a.country.name, AVG(r.lengthFt) as average " +
            "FROM Airport a JOIN a.runwayList r " +
            "WHERE (?1 IS NULL OR r.leHeadingDegT >= ?1) " +
            "  AND (?2 IS NULL OR r.leHeadingDegT <= ?2) " +
            "GROUP BY a.country.code, a.country.name")
    Page<Object[]> getAverageRunwayLengthPerCountry(Double minHeading, Double maxHeading, Pageable pageable);

}

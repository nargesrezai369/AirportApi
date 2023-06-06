package com.example.airport.domain.repository;

import com.example.airport.domain.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

    @Query("SELECT c.name, COUNT(a) AS airportCount FROM Airport a JOIN a.country c WHERE NOT (a.type = 'closed')" +
            " GROUP BY c.name")
    Page<Object[]> findTopCountriesWithHighestAirportCount(Pageable pageable);

}

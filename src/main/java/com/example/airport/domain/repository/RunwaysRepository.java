package com.example.airport.domain.repository;

import com.example.airport.domain.entity.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Repository
public interface RunwaysRepository extends JpaRepository<Runway, Long> {

}

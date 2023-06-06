package com.example.airport.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Entity
@Table(name = "airport_comments")
@Getter
@Setter
@ToString
public class AirportComment extends BaseEntity {

    private String threadRef;
    private String airportRef;
    private String airportIdent;
    private String date;
    private String memberNickname;
    private String subject;
    private String body;

}

package com.example.airport.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

}
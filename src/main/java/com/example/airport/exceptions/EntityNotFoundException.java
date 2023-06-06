package com.example.airport.exceptions;

/**
 * @author : Narges Rezaei
 * @since : 6/5/2023
 **/
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
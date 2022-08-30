package com.example.busdrivermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This bus is already in use.")
public class BusAlreadyInUseException extends RuntimeException {

    public BusAlreadyInUseException(String message) {
        super(message);
    }

}

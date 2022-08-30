package com.example.busdrivermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    private final String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(BusAlreadyInUseException.class)
    public final ResponseEntity<ErrorData> handleBusAlreadyInUseException
            (BusAlreadyInUseException e)
    {

        ErrorData error = new ErrorData(BAD_REQUEST, e.getLocalizedMessage(), ZonedDateTime.now(ZoneId.of("Europe/Madrid")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
package com.example.busdrivermanager.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class ConstraintsViolationException extends RuntimeException
{

    public ConstraintsViolationException(String message)
    {
        super(message);
    }

}
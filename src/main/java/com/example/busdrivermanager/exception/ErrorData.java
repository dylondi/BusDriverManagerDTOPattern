package com.example.busdrivermanager.exception;

import java.time.ZonedDateTime;

public class ErrorData
{

    private String status;
    private String message;
    private ZonedDateTime time;

    public ErrorData(String status, String message, ZonedDateTime time) {
        super();
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }
}

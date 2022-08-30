package com.example.busdrivermanager.datatransferobject;


import com.example.busdrivermanager.domainvalue.GeoCoordinate;
import com.example.busdrivermanager.domainvalue.OnlineStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusDriverDTO
{
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;

    private Long currentBusId;

    private OnlineStatus onlineStatus;

    private Boolean deleted;


    private BusDriverDTO()
    {
    }


    private BusDriverDTO(Long id, String username, String password, GeoCoordinate coordinate, Long currentBusId, OnlineStatus onlineStatus, Boolean deleted)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.currentBusId = currentBusId;
        this.onlineStatus = onlineStatus;
        this.deleted = deleted;
    }


    public static BusDriverDTOBuilder newBuilder()
    {
        return new BusDriverDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }

    public Long getCurrentCarId() {
        return currentBusId;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public static class BusDriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;

        private Long currentBusId;

        private OnlineStatus onlineStatus;

        private Boolean deleted;



        public BusDriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public BusDriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public BusDriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public BusDriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }

        public BusDriverDTOBuilder setCurrentCarId(Long currentBusId)
        {
            this.currentBusId = currentBusId;
            return this;
        }

        public BusDriverDTOBuilder setOnlineStatus(OnlineStatus onlineStatus)
        {
            this.onlineStatus = onlineStatus;
            return this;
        }

        public BusDriverDTOBuilder setDeleted(Boolean deleted)
        {
            this.deleted = deleted;
            return this;
        }


        public BusDriverDTO createBusDriverDTO()
        {
            return new BusDriverDTO(id, username, password, coordinate, currentBusId, onlineStatus, deleted);
        }

    }
}
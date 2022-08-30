package com.example.busdrivermanager.datatransferobject;


import com.example.busdrivermanager.domainvalue.BusType;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusDTO {

    private Long id;

    @NotNull
    private String licensePlate;

    @NotNull
    private Integer seatCount;

    @NotNull
    private Boolean doubleDecker;

    private Double rating;

    private Integer numOfRatings;

    @NotNull
    private BusType busType;

    @NotNull
    private String manufacturer;

    private Long currentDriverId;

    public BusDTO(Long id, String licensePlate, Integer seatCount, Double rating, Integer numOfRatings, Boolean doubleDecker, BusType busType, String manufacturer, Long currentDriverId) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.doubleDecker = doubleDecker;
        this.busType = busType;
        this.manufacturer = manufacturer;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.currentDriverId = currentDriverId;
    }

    public static BusDTO.BusDTOBuilder newBuilder()
    {
        return new BusDTO.BusDTOBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getNumOfRatings() {
        return numOfRatings;
    }

    public Boolean getConvertible() {
        return doubleDecker;
    }

    public BusType getBusType() {
        return busType;
    }

    public String getManufacturer() {
        return manufacturer;
    }


    public Long getCurrentDriverId() {
        return currentDriverId;
    }

    public static class  BusDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private Integer seatCount;
        private Boolean doubleDecker;
        private Double rating;
        private Integer numOfRatings;
        private BusType busType;
        private String manufacturer;
        private Long currentDriverId;


        public BusDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public BusDTOBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public BusDTOBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public BusDTOBuilder setConvertible(Boolean doubleDecker) {
            this.doubleDecker = doubleDecker;
            return this;
        }

        public BusDTOBuilder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public BusDTOBuilder setNumOfRatings(Integer numOfRatings) {
            this.numOfRatings = numOfRatings;
            return this;
        }

        public BusDTOBuilder setBusType(BusType busType) {
            this.busType = busType;
            return this;
        }

        public BusDTOBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public BusDTOBuilder setCurrentDriverId(Long currentDriverId) {
            this.currentDriverId = currentDriverId;
            return this;
        }

        public BusDTO createBusDTO()
        {
            return new BusDTO(id, licensePlate, seatCount, rating, numOfRatings, doubleDecker, busType, manufacturer, currentDriverId);
        }

    }
}

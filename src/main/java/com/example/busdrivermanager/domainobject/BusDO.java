package com.example.busdrivermanager.domainobject;


import com.example.busdrivermanager.domainvalue.BusType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "bus")
public class BusDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Integer seatCount;

    @Column(nullable = false)
    private Boolean doubleDecker;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private Integer numOfRatings;

    @Column(nullable = false)
    private Integer runningRatingTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusType busType;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Boolean deleted = false;


    @OneToOne(mappedBy = "currentBus")
    private BusDriverDO currentDriver;





    public BusDO(String licensePlate, Integer seatCount, Boolean doubleDecker, BusType busType, String manufacturer) {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.doubleDecker = doubleDecker;
        rating = 0.0;
        numOfRatings = 0;
        runningRatingTotal = 0;
        this.busType = busType;
        this.manufacturer = manufacturer;
        this.deleted = false;
        this.currentDriver = null;
    }

    public BusDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public boolean isDoubleDecker() {
        return doubleDecker;
    }

    public void setDoubleDecker(boolean convertible) {
        this.doubleDecker = convertible;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public int getRunningRatingTotal() {
        return runningRatingTotal;
    }

    public void setRunningRatingTotal(int runningRatingTotal) {
        this.runningRatingTotal = runningRatingTotal;
    }

    public BusType getBusType() {
        return busType;
    }

    public void setBusType(BusType busType) {
        this.busType = busType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

    public BusDriverDO getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentDriver(BusDriverDO currentDriver) {
        this.currentDriver = currentDriver;
    }
}

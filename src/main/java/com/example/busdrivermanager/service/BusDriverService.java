package com.example.busdrivermanager.service;

import com.example.busdrivermanager.domainobject.BusDriverDO;
import com.example.busdrivermanager.exception.BusAlreadyInUseException;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;

public interface BusDriverService {


    BusDriverDO find(Long busDriverId) throws EntityNotFoundException;

    BusDriverDO create(BusDriverDO busDriverDO) throws ConstraintsViolationException;

    void delete(Long busDriverId) throws EntityNotFoundException;

    void updateLocation(long busDriverId, double longitude, double latitude) throws EntityNotFoundException;

    void updateCurrentCar(long busDriverId, long busId) throws EntityNotFoundException, BusAlreadyInUseException;

    void deselectCurrentCar(long busDriverId) throws EntityNotFoundException;
}

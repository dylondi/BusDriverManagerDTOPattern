package com.example.busdrivermanager.service;

import com.example.busdrivermanager.domainobject.BusDriverDO;
import com.example.busdrivermanager.exception.BusAlreadyInUseException;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;

public class BusDriverServiceImpl implements BusDriverService{
    @Override
    public BusDriverDO find(Long busDriverId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public BusDriverDO create(BusDriverDO busDriverDO) throws ConstraintsViolationException {
        return null;
    }

    @Override
    public void delete(Long busDriverId) throws EntityNotFoundException {

    }

    @Override
    public void updateLocation(long busDriverId, double longitude, double latitude) throws EntityNotFoundException {

    }

    @Override
    public void updateCurrentCar(long busDriverId, long busId) throws EntityNotFoundException, BusAlreadyInUseException {

    }

    @Override
    public void deselectCurrentCar(long busDriverId) throws EntityNotFoundException {

    }
}

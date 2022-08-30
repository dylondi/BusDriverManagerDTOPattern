package com.example.busdrivermanager.service;

import com.example.busdrivermanager.dataaccessobject.BusDriverRepository;
import com.example.busdrivermanager.domainobject.BusDO;
import com.example.busdrivermanager.domainobject.BusDriverDO;
import com.example.busdrivermanager.domainvalue.GeoCoordinate;
import com.example.busdrivermanager.domainvalue.OnlineStatus;
import com.example.busdrivermanager.exception.BusAlreadyInUseException;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BusDriverServiceImpl implements BusDriverService{
    
    private static final Logger LOG = LoggerFactory.getLogger(BusDriverServiceImpl.class);
    
    private final BusDriverRepository busDriverRepository;

    public BusDriverServiceImpl(BusDriverRepository busDriverRepository) {
        this.busDriverRepository = busDriverRepository;
    }

    @Override
    public BusDriverDO find(Long busDriverId) throws EntityNotFoundException {
        return findDriverChecked(busDriverId);
    }

    @Override
    public BusDriverDO create(BusDriverDO busDriverDO) throws ConstraintsViolationException {
        BusDriverDO busDriver;
        try
        {
            busDriver = busDriverRepository.save(busDriverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a busDriver: {}", busDriverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return busDriver;
    }

    @Override
    @Transactional
    public void delete(Long busDriverId) throws EntityNotFoundException {

        busDriverRepository.delete(findDriverChecked(busDriverId));
        
    }

    @Override
    @Transactional
    public void updateLocation(long busDriverId, double longitude, double latitude) throws EntityNotFoundException {

        BusDriverDO busDriverDO = findDriverChecked(busDriverId);
        busDriverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }

    @Override
    public void updateCurrentCar(long busDriverId, long busId) throws EntityNotFoundException, BusAlreadyInUseException {


//        BusDO busDO = carRepository.findById(busId)
//                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + busId));
//
//        if(busDO.getCurrentDriver()!=null){
//            LOG.error("CarAlreadyInUseException:  Car with carId: " + busId + ", is already in use.", busId);
//            throw new BusAlreadyInUseException("Car with carId: " + busId + ", is already in use.");
//        }else {
//
//            BusDriverDO busDriverDO = findDriverChecked(busDriverId);
//
//            busDriverDO.setCurrentCar(busDO);
//            busDO.setCurrentDriver(busDriverDO);
//
//            busDriverRepository.save(busDriverDO);
//            carRepository.save(busDO);
//            }
    }

    @Override
    public void deselectCurrentCar(long busDriverId) throws EntityNotFoundException {

    }

    @Override
    public void updateOnlineStatus(long busDriverId, OnlineStatus onlineStatus) throws EntityNotFoundException {
        BusDriverDO busDriverDO = findDriverChecked(busDriverId);
        busDriverDO.setOnlineStatus(onlineStatus);
        busDriverRepository.save(busDriverDO);
    }


    private BusDriverDO findDriverChecked(Long busDriverId) throws EntityNotFoundException
    {
        return busDriverRepository.findById(busDriverId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + busDriverId));
    }
}

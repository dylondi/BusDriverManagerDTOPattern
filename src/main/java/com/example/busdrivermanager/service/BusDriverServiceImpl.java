package com.example.busdrivermanager.service;

import com.example.busdrivermanager.dataaccessobject.BusDriverRepository;
import com.example.busdrivermanager.dataaccessobject.BusRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class BusDriverServiceImpl implements BusDriverService{

    private static final Logger LOG = LoggerFactory.getLogger(BusDriverServiceImpl.class);

    private final BusDriverRepository busDriverRepository;
    private final BusRepository busRepository;

    public BusDriverServiceImpl(BusDriverRepository busDriverRepository, BusRepository busRepository) {
        this.busDriverRepository = busDriverRepository;
        this.busRepository = busRepository;
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

    @Override
    public List<BusDriverDO> findByOnlineStatus(OnlineStatus onlineStatus) {
        return busDriverRepository.findByOnlineStatus(onlineStatus);    }

    @Override
    public List<BusDriverDO> findByOnlineStatusOrUsernameOrLicensePlateOrRating(OnlineStatus onlineStatus, String username, String licensePlate, Double rating) {
        List<BusDriverDO> res = busDriverRepository.findByOnlineStatusOrUsernameIgnoreCase(onlineStatus, username);
        List<BusDO> busDOs = busRepository.findByLicensePlateOrRating(licensePlate, rating);

        for(BusDO busDO : busDOs){

            BusDriverDO currentDriver = busDO.getCurrentDriver();

            if(currentDriver!=null && res.stream().filter(car -> currentDriver.getId().equals(car.getId())).findAny().orElse(null)==null)
                res.add(busDO.getCurrentDriver());
        }

        return res;
    }

    @Override
    public List<BusDriverDO> findByOnlineStatusAndUsernameAndLicensePlateAndRating(OnlineStatus onlineStatus, String username, String licensePlate, Double rating) {

        List<BusDriverDO> res;
        List<BusDO> carDOs;

        boolean onlineStatusPresent = false;
        boolean usernamePresent = false;
        boolean licensePlatePresent = false;
        boolean ratingPresent = false;

        if(onlineStatus != null)
            onlineStatusPresent = true;

        if(username != null)
            usernamePresent = true;

        if(licensePlate != null)
            licensePlatePresent = true;

        if(rating != null)
            ratingPresent = true;


        if(onlineStatusPresent && usernamePresent) {
            res = busDriverRepository.findByOnlineStatusAndUsernameIgnoreCase(onlineStatus, username);
        }
        else if(onlineStatusPresent || usernamePresent){
            res = busDriverRepository.findByOnlineStatusOrUsernameIgnoreCase(onlineStatus, username);
        }
        else{
            res = new ArrayList<>();
        }


        if(licensePlatePresent && ratingPresent) {
            carDOs = busRepository.findByLicensePlateAndRating(licensePlate, rating);
        }
        else if(licensePlatePresent || ratingPresent){
            carDOs = busRepository.findByLicensePlateOrRating(licensePlate, rating);
        }
        else{
            return res;
        }


        if(licensePlatePresent || ratingPresent) {
            removeInvalidDrivers(licensePlate, rating, res, licensePlatePresent, ratingPresent);
        }

        if(carDOs!=null) {
            addDriversIfValid(onlineStatus, username, res, carDOs, onlineStatusPresent, usernamePresent);
        }

        return res;
    }


    private void addDriversIfValid(OnlineStatus onlineStatus, String username, List<BusDriverDO> res, List<BusDO> busDOs, boolean onlineStatusPresent, boolean usernamePresent) {
        //loop through car search
        for(BusDO busDO : busDOs) {

            //get current driver
            BusDriverDO currentDriver = busDO.getCurrentDriver();

            if (currentDriver != null && res.stream().filter(driverDO -> currentDriver.getId().equals(driverDO.getId())).findAny().orElse(null) == null) {
                if(onlineStatusPresent && usernamePresent){
                    if(currentDriver.getOnlineStatus().equals(onlineStatus) && currentDriver.getUsername().equals(username)){
                        res.add(currentDriver);
                    }
                }
                else if (onlineStatusPresent){
                    if(currentDriver.getOnlineStatus().equals(onlineStatus)){
                        res.add(currentDriver);
                    }
                }
                else if (usernamePresent){
                    if(currentDriver.getUsername().equals(username)){
                        res.add(currentDriver);
                    }
                }else{
                    res.add(currentDriver);
                }
            }
        }
    }

    private void removeInvalidDrivers(String licensePlate, Double rating, List<BusDriverDO> res, boolean licensePlatePresent, boolean ratingPresent) {
        if(licensePlatePresent && ratingPresent){
            res.removeIf(driverDO -> driverDO.getCurrentCar()==null || !driverDO.getCurrentCar().getLicensePlate().equals(licensePlate) || !(driverDO.getCurrentCar().getRating() == rating));

        }
        else if (licensePlatePresent){
            res.removeIf(driverDO -> driverDO.getCurrentCar()==null || !driverDO.getCurrentCar().getLicensePlate().equals(licensePlate));

        }
        else if (ratingPresent){
            res.removeIf(driverDO -> driverDO.getCurrentCar()==null || !(driverDO.getCurrentCar().getRating() == rating));
        }
    }

    private BusDriverDO findDriverChecked(Long busDriverId) throws EntityNotFoundException
    {
        return busDriverRepository.findById(busDriverId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + busDriverId));
    }
}

package com.example.busdrivermanager.service;

import com.example.busdrivermanager.dataaccessobject.BusDriverRepository;
import com.example.busdrivermanager.dataaccessobject.BusRepository;
import com.example.busdrivermanager.domainobject.BusDO;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BusServiceImpl implements BusService{

    private static final Logger LOG = LoggerFactory.getLogger(BusServiceImpl.class);

    private final BusRepository busRepository;
    private final BusDriverRepository busDriverRepository;

    public BusServiceImpl(BusRepository busRepository, BusDriverRepository busDriverRepository) {
        this.busRepository = busRepository;
        this.busDriverRepository = busDriverRepository;
    }


    @Override
    public BusDO find(Long busId) throws EntityNotFoundException {
        return findBusChecked(busId);
    }

    @Override
    public BusDO create(BusDO busDO) throws ConstraintsViolationException {
        BusDO busDO2;
        try
        {
            busDO2 = busRepository.save(busDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a bus: {}", busDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return busDO2;
    }

    @Override
    @Transactional
    public void delete(Long busId) throws EntityNotFoundException {
        busRepository.delete(findBusChecked(busId));
    }

    @Override
    @Transactional
    public void updateRating(long busId, int newRating) throws EntityNotFoundException {
        BusDO busDO = findBusChecked(busId);

        busDO.setNumOfRatings(busDO.getNumOfRatings()+1);
        busDO.setRunningRatingTotal(busDO.getRunningRatingTotal()+newRating);

        if(busDO.getNumOfRatings() == 1){
            busDO.setRating(newRating);
        }else{
            busDO.setRating(calculateNewRating(busDO));
        }
    }

    private BusDO  findBusChecked(Long busId) throws EntityNotFoundException {
        return busRepository.findById(busId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + busId));
    }


    private double calculateNewRating(BusDO busDO) {

        return ((double)busDO.getRunningRatingTotal())/busDO.getNumOfRatings();
    }

}

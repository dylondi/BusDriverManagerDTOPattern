package com.example.busdrivermanager.service;

import com.example.busdrivermanager.domainobject.BusDO;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;

public interface BusService {

    BusDO find(Long busId) throws EntityNotFoundException;

    BusDO create(BusDO busDO) throws ConstraintsViolationException;

    void delete(Long busId) throws EntityNotFoundException;

    void updateRating(long busId, int rating) throws EntityNotFoundException;
    
}

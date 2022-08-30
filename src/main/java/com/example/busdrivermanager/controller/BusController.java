package com.example.busdrivermanager.controller;


import com.example.busdrivermanager.controller.mapper.BusMapper;
import com.example.busdrivermanager.datatransferobject.BusDTO;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;
import com.example.busdrivermanager.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * All operations with a bus driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class BusController {


    private final BusService busService;

    @Autowired
    public BusController(final BusService busService){
        this.busService = busService;
    }

    @GetMapping("/{busId}")
    public BusDTO getBus(@PathVariable long busId) throws EntityNotFoundException
    {
        return BusMapper.makeBusDTO(busService.find(busId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusDTO createBus(@Valid @RequestBody BusDTO busDTO) throws ConstraintsViolationException
    {
        return BusMapper.makeBusDTO(busService.create(BusMapper.makeBusDO(busDTO)));
    }


    @DeleteMapping("/{busId}")
    public void deleteBusDriver(@PathVariable long busId) throws EntityNotFoundException
    {
        busService.delete(busId);
    }


    @PutMapping("/{busId}")
    public void updateRating(
            @PathVariable long busId, @RequestParam int rating)
            throws EntityNotFoundException
    {
        busService.updateRating(busId, rating);
    }
}

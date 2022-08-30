package com.example.busdrivermanager.controller;

import com.example.busdrivermanager.controller.mapper.BusDriverMapper;
import com.example.busdrivermanager.datatransferobject.BusDriverDTO;
import com.example.busdrivermanager.datatransferobject.BusDriverDTO;
import com.example.busdrivermanager.domainobject.BusDriverDO;
import com.example.busdrivermanager.domainvalue.OnlineStatus;
import com.example.busdrivermanager.exception.BusAlreadyInUseException;
import com.example.busdrivermanager.exception.ConstraintsViolationException;
import com.example.busdrivermanager.exception.EntityNotFoundException;
import com.example.busdrivermanager.service.BusDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/drivers")
public class BusDriverController {


    private final BusDriverService busDriverService;

    @Autowired
    public BusDriverController(BusDriverService busDriverService) {
        this.busDriverService = busDriverService;
    }


    @GetMapping("/{busDriverId}")
    public BusDriverDTO getBusDriver(@PathVariable long busDriverId) throws EntityNotFoundException
    {
        return BusDriverMapper.makeBusDriverDTO(busDriverService.find(busDriverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusDriverDTO createBusDriver(@Valid @RequestBody BusDriverDTO busBusDriverDTO) throws ConstraintsViolationException
    {
        BusDriverDO driverDO = BusDriverMapper.makeBusDriverDO(busBusDriverDTO);
        return BusDriverMapper.makeBusDriverDTO(busDriverService.create(driverDO));
    }


    @DeleteMapping("/{busDriverId}")
    public void deleteBusDriver(@PathVariable long busDriverId) throws EntityNotFoundException
    {
        busDriverService.delete(busDriverId);
    }


    @PutMapping("/{busDriverId}")
    public void updateLocation(
            @PathVariable long busDriverId, @RequestParam double longitude, @RequestParam double latitude)
            throws EntityNotFoundException
    {
        busDriverService.updateLocation(busDriverId, longitude, latitude);
    }

    @PutMapping("/select_car/{busDriverId}")
    public void updateCurrentBus(
            @PathVariable long busDriverId, @RequestParam long busId)
            throws EntityNotFoundException, BusAlreadyInUseException {
        busDriverService.updateCurrentCar(busDriverId, busId);
    }
    @PutMapping("/deselect_car/{busDriverId}")
    public void updateCurrentBusDeselect(
            @PathVariable long busDriverId)
            throws EntityNotFoundException
    {
        busDriverService.deselectCurrentCar(busDriverId);
    }


    @PutMapping("/online_status/{busDriverId}")
    public void updateOnlineStatus(
            @PathVariable long busDriverId, @RequestParam OnlineStatus onlineStatus)
            throws EntityNotFoundException
    {
        busDriverService.updateOnlineStatus(busDriverId, onlineStatus);
    }


    @GetMapping
    public List<BusDriverDTO> findDrivers(@RequestParam(required = false) OnlineStatus onlineStatus)
    {
        return BusDriverMapper.makeBusDriverDTOList(busDriverService.findByOnlineStatus(onlineStatus));
    }

    @GetMapping("/find_or")
    public List<BusDriverDTO> getDriversByOnlineStatusOrUsernameOrLicensePlateOrRating(@RequestParam(required = false) OnlineStatus onlineStatus,
                                                                                    @RequestParam(required = false) String username,
                                                                                    @RequestParam(required = false) String licensePlate,
                                                                                    @RequestParam(required = false) Double rating){
        return BusDriverMapper.makeBusDriverDTOList(busDriverService.findByOnlineStatusOrUsernameOrLicensePlateOrRating(onlineStatus, username, licensePlate, rating));
    }

    @GetMapping("/find_and")
    public List<BusDriverDTO> getDriversByOnlineStatusAndUsernameAndLicensePlateAndRating(@RequestParam(required = false) OnlineStatus onlineStatus,
                                                                                       @RequestParam(required = false) String username,
                                                                                       @RequestParam(required = false) String licensePlate,
                                                                                       @RequestParam(required = false) Double rating){
        return BusDriverMapper.makeBusDriverDTOList(busDriverService.findByOnlineStatusAndUsernameAndLicensePlateAndRating(onlineStatus, username, licensePlate, rating));
    }

}

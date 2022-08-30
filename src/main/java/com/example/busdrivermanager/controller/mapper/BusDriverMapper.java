package com.example.busdrivermanager.controller.mapper;

import com.example.busdrivermanager.datatransferobject.BusDriverDTO;
import com.example.busdrivermanager.domainobject.BusDriverDO;
import com.example.busdrivermanager.domainvalue.GeoCoordinate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BusDriverMapper {

    public static BusDriverDO makeBusDriverDO(BusDriverDTO busDriverDTO)
    {
        return new BusDriverDO(busDriverDTO.getUsername(), busDriverDTO.getPassword());
    }


    public static BusDriverDTO makeBusDriverDTO(BusDriverDO driverDO)
    {

        BusDriverDTO.BusDriverDTOBuilder busDriverDTOBuilder = BusDriverDTO.newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername())
                .setOnlineStatus(driverDO.getOnlineStatus())
                .setDeleted(driverDO.getDeleted());


        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            busDriverDTOBuilder.setCoordinate(coordinate);
        }

        if(driverDO.getCurrentCar()!=null){
            busDriverDTOBuilder.setCurrentCarId(driverDO.getCurrentCar().getId());
        }

        return busDriverDTOBuilder.createBusDriverDTO();
    }


    public static List<BusDriverDTO> makeBusDriverDTOList(Collection<BusDriverDO> drivers)
    {
        return drivers.stream()
                .map(BusDriverMapper::makeBusDriverDTO)
                .collect(Collectors.toList());
    }
}


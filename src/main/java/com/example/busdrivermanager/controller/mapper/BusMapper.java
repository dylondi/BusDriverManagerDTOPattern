package com.example.busdrivermanager.controller.mapper;

import com.example.busdrivermanager.datatransferobject.BusDTO;
import com.example.busdrivermanager.domainobject.BusDO;

public class BusMapper {

    public static BusDO makeBusDO(BusDTO busDTO){
        return new BusDO(busDTO.getLicensePlate(), busDTO.getSeatCount(), busDTO.getConvertible(),busDTO.getBusType(), busDTO.getManufacturer());
    }

    public static BusDTO makeBusDTO(BusDO busDO){

        BusDTO.BusDTOBuilder busDTOBuilder = BusDTO.newBuilder()
                .setId(busDO.getId())
                .setLicensePlate(busDO.getLicensePlate())
                .setSeatCount(busDO.getSeatCount())
                .setConvertible(busDO.isDoubleDecker())
                .setRating(busDO.getRating())
                .setNumOfRatings(busDO.getNumOfRatings())
                .setBusType(busDO.getBusType())
                .setManufacturer(busDO.getManufacturer());



        if(busDO.getCurrentDriver()!=null){
            busDTOBuilder.setCurrentDriverId(busDO.getCurrentDriver().getId());
            System.out.println("driver is:" + busDO.getCurrentDriver().getId());

        }

        BusDTO busDTO = busDTOBuilder.createBusDTO();

        return busDTO;
    }
}
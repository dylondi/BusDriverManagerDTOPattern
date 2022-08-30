package com.example.busdrivermanager.dataaccessobject;

import com.example.busdrivermanager.domainobject.BusDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<BusDO, Long> {

    List<BusDO> findByLicensePlateOrRating(String licensePlate, Double rating);
    List<BusDO> findByLicensePlateAndRating(String licensePlate, Double rating);
}

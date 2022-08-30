package com.example.busdrivermanager.dataaccessobject;

import com.example.busdrivermanager.domainobject.BusDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusDO, Long> {
}

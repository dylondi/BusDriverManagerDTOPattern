package com.example.busdrivermanager.dataaccessobject;

import com.example.busdrivermanager.domainobject.BusDriverDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusDriverRepository extends JpaRepository<BusDriverDO, Long> {
}

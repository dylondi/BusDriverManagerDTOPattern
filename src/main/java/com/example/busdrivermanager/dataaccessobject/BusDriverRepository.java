package com.example.busdrivermanager.dataaccessobject;

import com.example.busdrivermanager.domainobject.BusDriverDO;
import com.example.busdrivermanager.domainvalue.OnlineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusDriverRepository extends JpaRepository<BusDriverDO, Long> {

    List<BusDriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    List<BusDriverDO> findByOnlineStatusOrUsernameIgnoreCase(OnlineStatus onlineStatus, String username);
    List<BusDriverDO> findByOnlineStatusAndUsernameIgnoreCase(OnlineStatus onlineStatus, String username);

}

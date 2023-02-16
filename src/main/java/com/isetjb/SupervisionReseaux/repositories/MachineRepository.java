package com.isetjb.SupervisionReseaux.repositories;


import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {
    Optional<Machine> findMachinesByUser(User user);
}

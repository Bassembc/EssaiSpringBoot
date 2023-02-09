package com.isetjb.SupervisionReseaux.repositories;


import com.isetjb.SupervisionReseaux.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {

}

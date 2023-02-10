package com.isetjb.SupervisionReseaux.repositories;


import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.Plage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlageRepository extends JpaRepository<Plage,Long> {
   /* @Query(value="SELECT * FROM machines WHERE plage_id=:id", nativeQuery = true)
    ArrayList<Machine> findMachines(@Param("id") Long id);*/
}

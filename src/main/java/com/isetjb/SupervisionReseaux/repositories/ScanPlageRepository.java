package com.isetjb.SupervisionReseaux.repositories;

import com.isetjb.SupervisionReseaux.entities.ScanPlage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanPlageRepository extends JpaRepository<ScanPlage,Long> {
}

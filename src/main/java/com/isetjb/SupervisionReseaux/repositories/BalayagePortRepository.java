package com.isetjb.SupervisionReseaux.repositories;

import com.isetjb.SupervisionReseaux.entities.BalayagePort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalayagePortRepository extends JpaRepository<BalayagePort,Long> {
}

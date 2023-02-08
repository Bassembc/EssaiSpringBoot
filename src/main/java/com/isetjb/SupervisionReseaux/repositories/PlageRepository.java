package com.isetjb.SupervisionReseaux.repositories;


import com.isetjb.SupervisionReseaux.entities.Plage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlageRepository extends JpaRepository<Plage,Long> {
}

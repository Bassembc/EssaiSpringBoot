package com.isetjb.SupervisionReseaux.services;

import com.isetjb.SupervisionReseaux.entities.Plage;
import com.isetjb.SupervisionReseaux.repositories.PlageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class PlageService {
    @Autowired
    PlageRepository plageRepository;

    public Optional<Plage> getPlage(Long id) {
        return plageRepository.findById(id);
    }

    public Iterable<Plage> getPlages() {
        return plageRepository.findAll();
    }

    public void deletePlage( Long id) {
        plageRepository.deleteById(id);
    }

    public Plage savePlage(Plage plage) {
        Plage savedPlage = plageRepository.save(plage);
        return savedPlage;
    }
}

package com.isetjb.SupervisionReseaux.services;


import com.isetjb.SupervisionReseaux.entities.ScanPlage;
import com.isetjb.SupervisionReseaux.repositories.ScanPlageRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class ScanPlageService {
    @Autowired
    ScanPlageRepository scanPlageRepository;


    public Optional<ScanPlage> getScanPlage(Long id) {
        return scanPlageRepository.findById(id);
    }

    public Iterable<ScanPlage> getScanPlages() {
        return scanPlageRepository.findAll();
    }

    public void deleteScanPlage( Long id) {
        scanPlageRepository.deleteById(id);
    }

    public ScanPlage saveScanPlage(ScanPlage scanPlage) {
        ScanPlage savedScanPlage = scanPlageRepository.save(scanPlage);
        return savedScanPlage;
    }
}

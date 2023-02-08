package com.isetjb.SupervisionReseaux.services;

import com.isetjb.SupervisionReseaux.entities.BalayagePort;
import com.isetjb.SupervisionReseaux.repositories.BalayagePortRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class BalayagePortService {
    @Autowired
    BalayagePortRepository balayagePortRepository;


    public Optional<BalayagePort> getBalayagePort(Long id) {
        return balayagePortRepository.findById(id);
    }

    public Iterable<BalayagePort> getBalayagePorts() {
        return balayagePortRepository.findAll();
    }

    public void deleteBalayagePort( Long id) {
        balayagePortRepository.deleteById(id);
    }

    public BalayagePort saveBalayagePort(BalayagePort balayagePort) {
        BalayagePort savedBalayagePort = balayagePortRepository.save(balayagePort);
        return savedBalayagePort;
    }
}

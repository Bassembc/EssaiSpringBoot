package com.isetjb.SupervisionReseaux.services;

import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.repositories.MachineRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class MachineService {
    @Autowired
    MachineRepository machineRepository;

    public Optional<Machine> getMachine(Long id) {
        return machineRepository.findById(id);
    }

    public Iterable<Machine> getMachines() {
        return machineRepository.findAll();
    }

    public void deleteMachine( Long id) {
        machineRepository.deleteById(id);
    }

    public Machine saveMachine(Machine machine) {
        Machine savedMachine = machineRepository.save(machine);
        return savedMachine;
    }
}

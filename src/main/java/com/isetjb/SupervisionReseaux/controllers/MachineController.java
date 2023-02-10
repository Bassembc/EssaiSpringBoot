package com.isetjb.SupervisionReseaux.controllers;



import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.Plage;
import com.isetjb.SupervisionReseaux.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MachineController {

    @Autowired
    MachineService machineService;

    @GetMapping("/machines")
    public Iterable<Machine> getMachine() {
        return machineService.getMachines();
    }

    @GetMapping("/machine/{id}")
    public Machine getMachine(@PathVariable("id") Long id) {
        Optional<Machine> machine = machineService.getMachine(id);
        if (machine.isPresent()) {
            return machine.get();
        } else {
            return null;
        }

    }

    @DeleteMapping("/machine/{id}")
    public void deleteMachine(@PathVariable("id") Long id) {

        machineService.deleteMachine(id);
    }

    @PutMapping("/machine/{id}")
    public Machine updateMachine(@PathVariable("id") Long id, @RequestBody Machine machine) {
        Optional<Machine> e = machineService.getMachine(id);
        if (e.isPresent()) {
            Machine currentMachine = e.get();

            String ipAdress = machine.getHostName();

            if (ipAdress != null) {
                currentMachine.setHostName(ipAdress);
            }
            Plage plage = machine.getPlage();
            if (plage != null) {
                currentMachine.setPlage(plage);

            }
        }
        return machine;
    }
}
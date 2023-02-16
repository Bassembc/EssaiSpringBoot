package com.isetjb.SupervisionReseaux.controllers;



import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.User;
import com.isetjb.SupervisionReseaux.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class MachineController {

    @Autowired
    MachineService machineService;

    @PostMapping("/ajouterMachine")
    public Machine addMachine(@RequestBody Machine machine){
        String hostName= machine.getIpAddresse();
        LocalDateTime dateConnexion=machine.getDateDebutConnexion();
        User user=machine.getUser();
        Machine machineAjouter=new Machine();
        machineAjouter.setUser(user);
        machineAjouter.setIpAddresse(hostName);
        machineAjouter.setDateDebutConnexion(dateConnexion);
        machineService.saveMachine(machineAjouter);
        return machineAjouter;
    }
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
            String hostName = machine.getIpAddresse();
            LocalDateTime dateTime=LocalDateTime.now();
            User user=machine.getUser();

            if (hostName != null) {
                currentMachine.setIpAddresse(hostName);
            }
            if(dateTime !=null){
                currentMachine.setDateDebutConnexion(dateTime);
            }
            if(user !=null){
                currentMachine.setUser(user);
            }
        machineService.saveMachine(currentMachine);

        }
        return machine;
    }
}
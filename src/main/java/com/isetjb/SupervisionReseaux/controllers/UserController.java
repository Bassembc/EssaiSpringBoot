package com.isetjb.SupervisionReseaux.controllers;


import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.User;
import com.isetjb.SupervisionReseaux.repositories.UserRepository;
import com.isetjb.SupervisionReseaux.services.MachineService;
import com.isetjb.SupervisionReseaux.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MachineService machineService;
    @PostMapping("/login")
    public Optional<User> login(@RequestBody User user){
        String userName=user.getUserName();
        String password=user.getPassword();
        Optional<User> utilisateur= userService.getUserConnected(userName,password);
        if(utilisateur.isPresent()){
            userService.saveUser(utilisateur.get());
            Machine machine =new Machine();
            machine.setUser(utilisateur.get());
            machine.setDateDebutConnexion(LocalDateTime.now());
            machineService.saveMachine(machine);
            return Optional.of(utilisateur.get());
        }
        return null;

    }


}

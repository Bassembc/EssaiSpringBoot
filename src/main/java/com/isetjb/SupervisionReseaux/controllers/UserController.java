package com.isetjb.SupervisionReseaux.controllers;


import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.User;
import com.isetjb.SupervisionReseaux.repositories.UserRepository;
import com.isetjb.SupervisionReseaux.services.MachineService;
import com.isetjb.SupervisionReseaux.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            Machine machine =new Machine();
            machine.setUser(utilisateur.get());
            machine.setDateDebutConnexion(LocalDateTime.now());
            machineService.saveMachine(machine);
            return Optional.of(utilisateur.get());
        }
        return null;

    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        userService.saveUser(user);
        return user;

    }
    @PutMapping("/user/{id}")
    public User  updateMachine(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> e = userService.getUser(id);
        if (e.isPresent()) {
            User currentUser = e.get();
            String userName = user.getUserName();
            String password=user.getPassword();
            Machine machine=user.getMachine();

            if (userName != null) {
                currentUser.setUserName(userName);
            }
            if(password !=null){
                currentUser.setPassword(password);
            }
            if(machine !=null){
                currentUser.setMachine(machine);
            }

     userService.saveUser(currentUser);
        }
        return user;
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
    }
}

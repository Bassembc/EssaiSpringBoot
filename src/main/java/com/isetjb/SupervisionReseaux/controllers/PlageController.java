package com.isetjb.SupervisionReseaux.controllers;

import com.isetjb.SupervisionReseaux.entities.Plage;
import com.isetjb.SupervisionReseaux.services.PlageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
@RestController
public class PlageController {
    @Autowired
    PlageService plageService;
    @PostMapping("/ajouterPlage")
    public Plage addPlage(@RequestBody Plage plage){
        String adresse= plage.getAddresse();
        Plage ajouterPlage=new Plage();
        ajouterPlage.setAddresse(adresse);
        plageService.savePlage(ajouterPlage);
        return ajouterPlage;
    }
    @GetMapping("/plages")
    public Iterable<Plage> getPlage() {
        return plageService.getPlages();
    }

    @GetMapping("/plages/{id}")
    public Plage getPlage(@PathVariable("id") Long id) {
        Optional<Plage> plage = plageService.getPlage(id);
        if (plage.isPresent()) {
            return plage.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/plage/{id}")
    public void deletePlage(@PathVariable("id") Long id) {

        plageService.deletePlage(id);
    }

    @PutMapping("/plage/{id}")
    public Plage updatePlage(@PathVariable("id") Long id, @RequestBody Plage plage) {
        Optional<Plage> e = plageService.getPlage(id);
        if (e.isPresent()) {
            Plage currentPlage = e.get();

            String startAddress = plage.getAddresse();

            if (startAddress!=null ) {
                currentPlage.setAddresse(startAddress);
            }

        }
        return plage;
    }
}

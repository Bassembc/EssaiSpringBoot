package com.isetjb.SupervisionReseaux.controllers;



import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.Plage;
import com.isetjb.SupervisionReseaux.entities.ScanPlage;
import com.isetjb.SupervisionReseaux.entities.User;
import com.isetjb.SupervisionReseaux.services.MachineService;
import com.isetjb.SupervisionReseaux.services.PlageService;
import com.isetjb.SupervisionReseaux.services.ScanPlageService;
import com.isetjb.SupervisionReseaux.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ScanPlageController {
    @Autowired
    ScanPlageService scanPlageService;
    @Autowired
    MachineService machineService;
    @Autowired
    UserService userService;
    @Autowired
    PlageService plageService;

    @PostMapping("/scanPlage")
    public ScanPlage checkHosts(@RequestBody Plage pplage) {
        User user = new User();
        user.setUserName("Foulen");
        user.setPassword("pasMotPasse");
        userService.saveUser(user);

        String[] adresseDebut =pplage.getStartAddress().split("\\.");
        String[] adresseFin =pplage.getEndAddress().split("\\.");

        Plage plage = new Plage();
        plage.setStartAddress(pplage.getStartAddress());
        plage.setEndAddress(pplage.getEndAddress());
        plageService.savePlage(plage);

        byte[] ipDebut = {
                (byte) Integer.parseInt(adresseDebut[0]),
                (byte) Integer.parseInt(adresseDebut[1]),
                (byte) Integer.parseInt(adresseDebut[2]),
                (byte) Integer.parseInt(adresseDebut[3])
        };
        byte[] ipFin = {
                (byte) Integer.parseInt(adresseFin[0]),
                (byte) Integer.parseInt(adresseFin[1]),
                (byte) Integer.parseInt(adresseFin[2]),
                (byte) Integer.parseInt(adresseFin[3])
        };

        ScanPlage scanPlage = new ScanPlage();
        scanPlage.setDateScan(LocalDateTime.now());
        scanPlage.setPlage(plage);
        scanPlage.setUser(user);

        List<Machine> machineList= new ArrayList<>();
        for (int i = ipDebut[3]; i < ipFin[3]; i++) {
            ipDebut[3]=(byte)i;
            try {
                InetAddress address = InetAddress.getByAddress(ipDebut);
                if (isAlive(address.getHostAddress())) {
                    Machine machine = new Machine();
                    machine.setPlage(plage);
                    machine.setDateDebutConnexion(LocalDateTime.now());
                    machine.setIpAddress(address.getHostAddress());
                    machineList.add(machine);
                    machineService.saveMachine(machine);

                }

            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }
        }
        scanPlageService.saveScanPlage(scanPlage);
        return scanPlage;
    }
    private boolean isAlive(String Ipv4Adr) {
        Process p1;
        boolean reachable = false;
        try {
            p1 = java.lang.Runtime.getRuntime().exec("ping -w 2 -n 2 " + Ipv4Adr);
            int returnVal = p1.waitFor();
            reachable = (returnVal == 0);
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
        return reachable;
    }

}

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
import org.springframework.web.bind.annotation.*;


import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;


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
    public ScanPlage checkHosts(@RequestBody Plage laPlage) {


        User user = new User();
        user.setUserName("Foulen");
        user.setPassword("pasMotPasse");
        userService.saveUser(user);
        String[] adresseDebut =laPlage.getAddresse().split("\\.");
        Plage plage = new Plage();
        plage.setAddresse(laPlage.getAddresse());

        ScanPlage scanPlage = new ScanPlage();
        scanPlage.setDateScan(LocalDateTime.now());
        scanPlage.setPlage(plage);
        scanPlage.setUser(user);
        List<Machine> machineList= new ArrayList<>();
        Thread loopPlage=new Thread(()->{

                for(int i = 1; i < 255; i++){
                    String param=String.valueOf(i);
                    Thread threadJob=new Thread(param){
                        @Override
                        public void run(){
                            try{
                                String ad=adresseDebut[0]+"."+adresseDebut[1]+"."+adresseDebut[2]+"."+param;
                                InetAddress address = InetAddress.getByName(ad);
                                if(address.isReachable(500)){
                                    System.out.println(address.getCanonicalHostName());
                                    Machine machine = new Machine();
                                    machine.setDateDebutConnexion(LocalDateTime.now());
                                    machine.setHostName(address.getHostName());
                                    machineService.saveMachine(machine);
                                    machineList.add(machine);



                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }
                    };
                    threadJob.setPriority(MIN_PRIORITY);
                    threadJob.start();

                }
            });
       loopPlage.setPriority(MAX_PRIORITY);
       loopPlage.start();


        plage.setMachines(machineList);
        plageService.savePlage(plage);
        scanPlageService.saveScanPlage(scanPlage);

        return scanPlage;

    }
    @GetMapping("/recupererLesScansPlage")
    public Iterable<ScanPlage> getScanPlages(){

        return scanPlageService.getScanPlages();
    }
    @GetMapping("/recupererScanPlage/{id}")
    public Optional<ScanPlage> getScanPlage(@PathVariable("id") Long id){

        return scanPlageService.getScanPlage(id);
    }
    @DeleteMapping("/ScanPlage/{id}")
    public void deleteScanPlage(@PathVariable("id") Long id){
        scanPlageService.deleteScanPlage(id);
    }
}



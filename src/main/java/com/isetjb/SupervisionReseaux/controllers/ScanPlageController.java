package com.isetjb.SupervisionReseaux.controllers;


import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.entities.Plage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ScanPlageController {

    @PostMapping("/scanMachines")
    public static List<String> checkHosts(Plage plage ,String dataFromFrontend) throws IOException, UnknownHostException {
        int timeout = 100;
        List<String> ipFounded =new ArrayList<>();
        for (int i = plage.getStartAddress(); i < plage.getEndAddress(); i++)
        {
            String host = dataFromFrontend + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout))
            {
                System.out.println("host"+host+"."+i+"is connected");
                ipFounded.add(host);


            }
        }
        return ipFounded;
    }


}

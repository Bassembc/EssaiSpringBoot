package com.isetjb.SupervisionReseaux.controllers;

import com.isetjb.SupervisionReseaux.entities.BalayagePort;
import com.isetjb.SupervisionReseaux.entities.Machine;
import com.isetjb.SupervisionReseaux.services.BalayagePortService;
import com.isetjb.SupervisionReseaux.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;


@RestController
public class BalayagePortController {
    @Autowired
    BalayagePortService balayagePortService;
    @Autowired
    MachineService machineService;
    @PostMapping("/balayerPortTcp/{id}")
    @Async
    public CompletableFuture<List<Integer>> balayagePortTcp(@PathVariable("id") Long id)  {
        Optional<Machine> machine = machineService.getMachine(id);
        BalayagePort balayagePort=new BalayagePort();
        List<Integer> listeTcp=new ArrayList<>();
        if(machine.isPresent()){
            balayagePort.setDateBalayage(LocalDateTime.now());
            balayagePort.setMachine(machine.get());

            Thread loopPort=new Thread(()->{
                for(int i = 0; i <= 1023; i++){
                    String param=String.valueOf(i);
                    Thread threadJob=new Thread(param){
                        @Override
                        public void run(){
                            try {
                                 int port=Integer.parseInt(param);
                                 InetSocketAddress socketAddress = new InetSocketAddress(machine.get().getIpAddresse(), port);
                                 Socket socket = new Socket();
                                 socket.connect(socketAddress, 1500);

                                 if(socket.isConnected()){
                                     listeTcp.add(port);
                                     socket.close();
                                 }

                            } catch (IOException e) {
                               //System.out.println(e.getMessage());
                            }

                        }
                    };
                    threadJob.setPriority(MIN_PRIORITY);
                    threadJob.start();

                }

            });
            loopPort.setPriority(MAX_PRIORITY);
            loopPort.start();

        }
        balayagePortService.saveBalayagePort(balayagePort);
        return CompletableFuture.completedFuture(listeTcp);
    }
    @PostMapping("/balayerPortUdp/{id}")
    @Async
    public CompletableFuture<List<Integer>> balayagePortUdp(@PathVariable("id") Long id){
        Optional<Machine> machine = machineService.getMachine(id);
        BalayagePort balayagePort=new BalayagePort();
        List<Integer> listeUdp=new ArrayList<>();
        if(machine.isPresent()){
            balayagePort.setDateBalayage(LocalDateTime.now());
            balayagePort.setMachine(machine.get());

            Thread loopPort=new Thread(()->{
                for(int i = 0; i <= 1024; i++){
                    String param=String.valueOf(i);
                    Thread threadJob=new Thread(param){
                        @Override
                        public void run(){
                            try {
                                int port=Integer.parseInt(param);
                                String dosCommand = "cmd.exe /s /c C:\\PortQry\\PortQry.exe -n "+machine.get().getIpAddresse()+" -p udp -e "+port;
                                Runtime runtime = Runtime.getRuntime();
                                Process process = runtime.exec(dosCommand);
                                java.io.InputStream in = process.getInputStream();
                                byte[] inBytes = in.readAllBytes();
                                String inString = new String(inBytes);

                                if (inString.indexOf("NOT LISTENING") > -1){
                                   System.out.println("Le port udp "+port+" est ferme");
                                }else{
                                  System.out.println("Le port udp "+port+" est ouvert");
                                    listeUdp.add(port);
                                }


                            } catch (IOException e) {
                                //System.out.println(e.getMessage());
                            }

                        }
                    };
                    threadJob.setPriority(MIN_PRIORITY);
                    threadJob.start();

                }

            });
            loopPort.setPriority(MAX_PRIORITY);
            loopPort.start();

        }
        balayagePortService.saveBalayagePort(balayagePort);
        return CompletableFuture.completedFuture(listeUdp);

    }
}

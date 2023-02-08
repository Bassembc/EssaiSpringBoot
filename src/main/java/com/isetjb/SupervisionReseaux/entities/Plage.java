package com.isetjb.SupervisionReseaux.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Plage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int startAddress;
    private int endAddress;
    //private String netIp;
    //private int choixUser;

    @OneToMany(mappedBy = "plage")
   private List<Machine> machines ;
}

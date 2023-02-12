package com.isetjb.SupervisionReseaux.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "machines")
public class Machine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String hostName;
    
    private LocalDateTime dateDebutConnexion;


    @ManyToOne()
    private Plage plage;


}

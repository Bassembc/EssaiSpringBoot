package com.isetjb.SupervisionReseaux.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "plages")
public class Plage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "adresseDebut")
    private String startAddress;
    @Column(name = "adresseFin")
    private String endAddress;

    @OneToMany(mappedBy = "plage")
   private List<Machine> machines ;
}

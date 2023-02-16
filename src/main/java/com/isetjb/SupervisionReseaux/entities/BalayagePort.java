package com.isetjb.SupervisionReseaux.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "balayagePorts")
public class BalayagePort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateBalayage;

    @OneToOne
    Machine machine;
    @ManyToOne
    User user;

}

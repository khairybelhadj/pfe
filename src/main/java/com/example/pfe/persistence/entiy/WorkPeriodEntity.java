package com.example.pfe.persistence.entiy;

import com.example.pfe.model.enumuration.Jour;
import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.Period;
import java.util.List;

@Entity
@Data
@Table(name = "work_period")
public class WorkPeriodEntity {
    @Id
    private Integer id;

    @Column(name = "nombres_cartes")
    private Integer nombresCartes;
    @Column(name = "cycle_temps_theorique")
    private Duration cycleTempsTheorique;
    @Column(name = "temps_theorique_realisation")
    private Duration tempsTheoriqueRealisation;
    private boolean face;
    private String shift;
    private Jour jour;
    @Column(name = "nombre_carte_bonne")
    private Integer nombreCarteBonne;
    @Column(name = "nombre_carte_mauvais")
    private Integer nombreCarteMauvais;

    @ManyToOne
    private ProductEntity productEntity;

    @ManyToOne
    private WorkerEntity workerEntity;
    @OneToMany(cascade = CascadeType.ALL)
    List<StopEntity> stopEntities;


}

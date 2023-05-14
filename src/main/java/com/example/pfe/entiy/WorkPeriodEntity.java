package com.example.pfe.entiy;

import com.example.pfe.model.enumuration.Jour;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Period;

@Entity
@Data
@Table(name = "work_period")
public class WorkPeriodEntity {
    @Id
    private Integer id;
    @Column(name = "nombres_cartes")
    private Integer nombresCartes;
    @Column(name = "cycle_temps_theorique")
    private Period cycleTempsTheorique;
    @Column(name = "temps_theorique_realisation")
    private Period tempsTheoriqueRealisation;
    private boolean face;
    private String shift;
    private Jour jour;
    @Column(name = "nombre_carte_bonne")
    private Integer nombreCarteBonne;
    @Column(name = "nombre_carte_mauvaie")
    private Integer nombreCarteMauvaie;
//    List<StopEntity> stopEntities;


//    ProductEntity productEntity;

}

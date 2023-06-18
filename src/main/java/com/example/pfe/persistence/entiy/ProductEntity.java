package com.example.pfe.persistence.entiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "nom_produit")
    private String nomProduit;
    @Column(name = "nb_carte")
    private Integer nb_carte;
    @Column(name = "cycleTempsTheoriqueTop")
    private  String cycleTempsTheoriqueTop;
    @Column(name = "cycleTempsTheoriqueBottom")
    private  String cycleTempsTheoriqueBottom;
    @OneToMany
    private List<WorkPeriodEntity> workPeriodEntities;


}

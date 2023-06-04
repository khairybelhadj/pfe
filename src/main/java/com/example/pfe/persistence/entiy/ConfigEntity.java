package com.example.pfe.persistence.entiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Period;

@Entity
@Data
@Table(name = "configuration")
public class ConfigEntity {
    @Id
    private Integer id;
    @Column(name = "nom_produit")
    private String nomProduit;
    @Column(name = "productId")
    private Integer productId;
    @Column(name = "cycleTempsTheorique")
    private Period cycleTempsTheorique;
    @Column(name = "workerName")
    private String workerName;
    @Column(name = "workerId")
    private Integer workerId;
    @Column(name = "password")
    private Integer password;

}

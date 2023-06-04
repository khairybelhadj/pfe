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
    private Integer id;
    @Column(name = "nom_produit")
    private String nomProduit;

    @OneToMany
    private List<WorkPeriodEntity> workPeriodEntities;


}

package com.example.pfe.entiy;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {
    @Id
    private Integer id;
    @Column(name = "nom_produit")
    private String nomProduit;
}

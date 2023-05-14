package com.example.pfe.model;

import com.example.pfe.model.enumuration.Jour;
import com.example.pfe.model.enumuration.Nom_Produit;
import lombok.*;

import java.time.Period;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Gestion_Quantite {
    private Nom_Produit nomProduit;
    private Integer nombresCartes;
    private Period cycleTempsTheorique;
    private Period tempsTheoriqueRealisation;
    private boolean face;
    private String shift;
    private Jour jour;
    private Integer nombreCarteBonne;
    private Integer nombreCarteMauvaie;

}

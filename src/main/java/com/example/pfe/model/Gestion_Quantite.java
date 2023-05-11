package com.example.pfe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Period;
@Getter
@Setter

public class Gestion_Quantite {
    private Nom_Produit nomProduit;
    private Integer nombresCartes;
    private Period cycleTempsTheorique;
    private Period tempsTheoriqueRealisation;
    private String commantaire;
    private Boolean top;
    private Boolean bot;
    private Jour jour;
    private Integer nombreCarteBonne;
    private Integer nombreCarteMauvaie;


public Gestion_Quantite(Nom_Produit nomProduit,Integer nombresCartes,Period cycleTempsTheorique,Period tempsTheoriqueRealisation,String commantaire){
    this.nomProduit = nomProduit;
    this.nombresCartes = nombresCartes;
    this.cycleTempsTheorique = cycleTempsTheorique;
    this.tempsTheoriqueRealisation = tempsTheoriqueRealisation;
    this.commantaire = commantaire;
}
    public Gestion_Quantite() {
        super();
    }

    @Override
    public String toString() {
        return "Gestion_Quantite{" +
                "nomProduit=" + nomProduit +
                ", nombresCartes=" + nombresCartes +
                ", cycleTempsTheorique=" + cycleTempsTheorique +
                ", tempsTheoriqueRealisation=" + tempsTheoriqueRealisation +
                ", commantaire='" + commantaire + '\'' +
                '}';
    }
}

package com.example.pfe.model.enumuration;

public enum Jour {

    LUNDI,
    MARDI,
    MERCREDI,
    JRUDI,
    VENDREDI,
    SAMEDI,
    DIMANCHE;
    private static final Jour[] ENUMS = Jour.values();


    public static Jour of(int alias) {
        return ENUMS[alias - 1];
    }
}
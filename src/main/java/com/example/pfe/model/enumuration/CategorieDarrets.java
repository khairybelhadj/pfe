package com.example.pfe.model.enumuration;

public enum CategorieDarrets {
    ARRETPROGRAMMES,
    ArretsProgrammes,
    ChangementDeSerie,
    DisfonctionementOrganisationnel,
    pannes,
    NonQualite,
    SousCharge;


    private static final CategorieDarrets[] ENUMS = CategorieDarrets.values();


    public static CategorieDarrets of(int alias) {
        return ENUMS[alias - 1];

    }
}

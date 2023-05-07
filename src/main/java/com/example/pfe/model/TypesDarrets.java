package com.example.pfe.model;


public enum TypesDarrets {
    Pauses,
    ReunionFormation,
    MaintenancePreventive1erNiveauOperateur,
    ValidationEI,
    Modificationdelaligne,
    ArretPlanifieParLaProd,
    DemarrageArretDeLigne,
    ChangementsDeSerie,
    StabililisationFour,
    AttenteManqueKitOF,
    ManqueduPersonnel,
    ManqueInstructionDeTravail,
    ProblemeOutillage,
    CoupureCourantProblemeCompresseur,
    ChangementDePlanningDeDernierMinute,
    RuptureComposant,
    ConvoyeurDepileur,
    Serigraphie,
    P1,
    P2,
    P3,
    FOUR,
    InspectionAOI,
    AutresDerives,
    ReprisePiecesEnDefautsQualite,
    ArretsProblemeQualite,
    ReajustementEquipementSuiteDefauts,
    FermetureDusine,
    PasDeBusunessSuffisant,
    MaintenancePreventive,
    JourFeries;


    @Override
    public String toString() {
        return super.toString();
    }
}

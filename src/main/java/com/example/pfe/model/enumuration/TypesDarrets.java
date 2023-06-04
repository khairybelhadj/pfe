package com.example.pfe.model.enumuration;


import java.util.Arrays;
import java.util.Optional;

public enum TypesDarrets {
    Pauses("Pause"),
    ReunionFormation("1"),
    MaintenancePreventive1erNiveauOperateur(""),
    ValidationEI(""),
    Modificationdelaligne(""),
    ArretPlanifieParLaProd(""),
    DemarrageArretDeLigne(""),
    ChangementsDeSerie(""),
    StabililisationFour(""),
    AttenteManqueKitOF(""),
    ManqueduPersonnel(""),
    ManqueInstructionDeTravail(""),
    ProblemeOutillage(""),
    CoupureCourantProblemeCompresseur(""),
    ChangementDePlanningDeDernierMinute(""),
    RuptureComposant(""),
    ConvoyeurDepileur(""),
    Serigraphie(""),
    P1(""),
    P2(""),
    P3(""),
    FOUR(""),
    InspectionAOI(""),
    AutresDerives(""),
    ReprisePiecesEnDefautsQualite(""),
    ArretsProblemeQualite(""),
    ReajustementEquipementSuiteDefauts(""),
    FermetureDusine(""),
    PasDeBusunessSuffisant(""),
    MaintenancePreventive(""),
    JourFeries("");

    String name;
    TypesDarrets(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public static Optional<TypesDarrets> get(String url) {
        return Arrays.stream(TypesDarrets.values())
                .filter(env -> env.name.equals(url))
                .findFirst();
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

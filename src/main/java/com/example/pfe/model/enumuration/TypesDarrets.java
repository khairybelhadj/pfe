package com.example.pfe.model.enumuration;


import java.util.Arrays;
import java.util.Optional;

public enum TypesDarrets {
    Pauses("Pause"),
    Reunion("Réunion"),
    Formation("Formation") ,
    MaintenancePreventive1erNiveauOperateur("Maintenance préventive niveaux 1"),
    ValidationEI("Validation EI"),
    Modificationdelaligne("Modification de la ligne"),
    ArretPlanifieParLaProd("Arrêt planifié par la production"),
    DemarrageArretDeLigne("Démarrage/Arrêt de la ligne"),
    ChangementsDeSerie("changement de la série"),
    StabililisationFour("stabilisation de ma four"),
    AttenteManqueKitOF("Arrêt dû à un manque de kit/outillage"),
    ManqueduPersonnel("absence de personnel"),
    ManqueInstructionDeTravail("instructions de travail manquantes"),
    ProblemeOutillage("problèmes avec l'outillage"),
    CoupureCourantProblemeCompresseur("coupure de courant/Problème Compresseur"),
    ChangementDePlanningDeDernierMinute("changement de planning de dernière minute"),
    RuptureComposant("rupture de composants"),
    ConvoyeurDepileur("Convoyeur/Dépileur"),
    Serigraphie("Sérigraphie"),
    P1("P1"),
    P2("P2"),
    P3("P3"),
    FOUR("Four"),
    InspectionAOI("Inspection AOI"),
    AutresDerives("Autres Dérives"),
    ameliorationProcessOptimisation("Amélioration Process/Optimisation"),
    autresDerives("Autres Dérives"),
    ReprisePiecesEnDefautsQualite("Reprise piéces en défauts Qualité"),
    ArretsProblemeQualite("Arrêts Problème Qualité"),
    ReajustementEquipementSuiteDefauts("Réajustement équipement suite défauts"),
    FermetureDusine("Fermeture d'usine"),
    PasDeBusunessSuffisant("Pas de Busuness suffisant"),
    MaintenancePreventive("Maintenance Préventive"),
    JourFeries("Jour féries");

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

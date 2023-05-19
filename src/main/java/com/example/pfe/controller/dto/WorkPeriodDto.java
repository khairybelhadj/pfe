package com.example.pfe.controller.dto;

import com.example.pfe.model.enumuration.Jour;

import javax.persistence.Column;
import java.time.Duration;

public class WorkPeriodDto {
    private Integer nombresCartes;
    private Duration cycleTempsTheorique;
    private Duration tempsTheoriqueRealisation;
    private boolean face;
    private String shift;
    private Jour jour;
    private Integer nombreCarteBonne;
    private Integer nombreCarteMauvaie;

    /**
     * Association
     */
    private Integer productId;
    private  Integer workerId;
}

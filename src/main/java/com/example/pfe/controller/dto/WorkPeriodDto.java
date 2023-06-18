package com.example.pfe.controller.dto;

import com.example.pfe.model.enumuration.Jour;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Data
public class WorkPeriodDto {
    private Integer id;
    private Integer nombresCartes;

    private String startTime;
    private Duration cycleTempsTheorique;
    private Duration tempsTheoriqueRealisation;
    private Boolean top;
    private Boolean bottom;

    private String face;
    private String shift;
    private Jour jour;
    private Integer nombreCarteBonne;
    private Integer nombreCarteMauvaie;
    private Integer stopCount;
    private List<StopDto> stopDtos;

    /**
     * Association
     */
    private Integer productId;
    private Integer workerId;
    private String nomProduit;
}

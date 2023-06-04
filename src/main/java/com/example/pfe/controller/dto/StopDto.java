package com.example.pfe.controller.dto;

import com.example.pfe.model.enumuration.CategorieDarrets;
import com.example.pfe.model.enumuration.TypesDarrets;
import lombok.Data;

import java.time.LocalTime;
@Data
public class StopDto {

    private Integer id;
    private String typesDarrets;

    private CategorieDarrets categorieDarrets;
    private String starttime;
    private String end_time;
    private String cause_stop;
    private String description;
    /**
     * just of assosiation
     */
    private Integer workPeriodId;
}

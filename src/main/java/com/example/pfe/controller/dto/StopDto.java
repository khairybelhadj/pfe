package com.example.pfe.controller.dto;

import com.example.pfe.model.enumuration.TypesDarrets;
import lombok.Data;

import java.time.LocalTime;
@Data
public class StopDto {

    private Integer id;
    private TypesDarrets typesDarrets;
    private LocalTime starttime;
    private LocalTime end_time;
    private String cause_stop;
    private String description;
    /**
     * just of assosiation
     */
    private Integer workPeriodId;
}

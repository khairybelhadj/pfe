package com.example.pfe.controller.dto;

import lombok.Data;

import java.time.Period;

@Data
public class ConfigDto {

    private String nomProduit;
    private Integer productId;
    private Period cycleTempsTheorique;
    private String workerName;
    private Integer workerId;
    private Integer password;

}

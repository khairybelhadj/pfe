package com.example.pfe.controller.dto;

import com.example.pfe.model.enumuration.Team;
import lombok.Data;

import java.time.Period;

@Data
public class WorkerDto {
    private String workerName;
    private Integer workerId;
    private String password;
    private String teams;
    private Team team;
    private String type;
}

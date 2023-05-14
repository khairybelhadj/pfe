package com.example.pfe.model;


import com.example.pfe.model.enumuration.TypesDarrets;
import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
public class Stop {
    private static Integer NB_STOP=1;

    private Integer id;
    private TypesDarrets typesDarrets;
    private LocalTime start_time;
    private LocalTime end_time;
    private String cause_stop;
    private String description;

    public Stop(TypesDarrets typesDarrets, LocalTime start_time, LocalTime end_time, String cause_stop, String description) {
        this.id=NB_STOP;
        NB_STOP = NB_STOP+1;
        this.typesDarrets = typesDarrets;
        this.start_time = start_time;
        this.end_time = end_time;
        this.cause_stop = cause_stop;
        this.description = description;
    }

    public Stop() {
        super();
    }

    @Override
    public String toString() {
        return "Stop{" +
                "typesDarrets=" + typesDarrets +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", cause_stop='" + cause_stop + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

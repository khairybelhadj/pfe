package com.example.pfe.persistence.entiy;

import com.example.pfe.controller.dto.StopDto;
import com.example.pfe.model.enumuration.CategorieDarrets;
import com.example.pfe.model.enumuration.TypesDarrets;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "stop")
@NoArgsConstructor
@Getter
@Setter
public class StopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "types_arrets")
    private TypesDarrets typesDarrets;
    private CategorieDarrets categorieDarrets;
    private LocalTime starttime;
    private LocalTime end_time;
    private String cause_stop;
    private String description;

    @ManyToOne
    @JoinColumn(name = "work_period_entity_id")
    private WorkPeriodEntity workPeriodEntity;

    public StopEntity(StopDto stopDto){
        this.typesDarrets=stopDto.getTypesDarrets();
        this.cause_stop=stopDto.getCause_stop();
        this.description=stopDto.getDescription();
        this.starttime=stopDto.getStarttime();
        this.end_time=stopDto.getEnd_time();
    }
}

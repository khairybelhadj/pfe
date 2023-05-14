package com.example.pfe.entiy;

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
    private LocalTime start_time;
    private LocalTime end_time;
    private String cause_stop;
    private String description;

}

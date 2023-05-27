package com.example.pfe.persistence.entiy;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class WorkerEntity {
    @Id
    @Column(name = "id")
    private Integer Id;
    private String login;
    private String password;

    @OneToMany
    private List<WorkPeriodEntity> workPeriodEntities;



}

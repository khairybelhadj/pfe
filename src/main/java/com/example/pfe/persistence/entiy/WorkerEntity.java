package com.example.pfe.persistence.entiy;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class WorkerEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer Id;
    private String workerName;
    private String password;
    private String teams;
    private String type;

    @OneToMany
    private List<WorkPeriodEntity> workPeriodEntities;


}

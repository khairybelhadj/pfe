package com.example.pfe.persistence.entiy;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WorkerEntity {
    @Id
    @Column(name = "id")
    private Integer Id;
    private String login;
    private String password;

    @OneToMany
    private WorkPeriodEntity workPeriodEntity;



}

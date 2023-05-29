package com.example.pfe.persistence.repo;

import com.example.pfe.model.enumuration.Jour;
import com.example.pfe.persistence.entiy.WorkPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkPeriodRepo extends JpaRepository<WorkPeriodEntity,Integer> {

    List<WorkPeriodEntity> findByJour(Jour jour);


    List<WorkPeriodEntity> findByDate(LocalDate localDate);


}

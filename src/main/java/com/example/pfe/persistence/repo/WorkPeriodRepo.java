package com.example.pfe.persistence.repo;

import com.example.pfe.persistence.entiy.WorkPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPeriodRepo extends JpaRepository<WorkPeriodEntity,Integer> {

}
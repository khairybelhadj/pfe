package com.example.pfe.persistence.repo;

import com.example.pfe.persistence.entiy.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepo extends JpaRepository<WorkerEntity, Integer> {

    List<WorkerEntity> findByworkerName(String workerName);

}

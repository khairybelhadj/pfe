package com.example.pfe.repo;

import com.example.pfe.entiy.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepo extends JpaRepository<StopEntity,Integer> {

}

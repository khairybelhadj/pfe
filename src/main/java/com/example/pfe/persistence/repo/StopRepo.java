package com.example.pfe.persistence.repo;

import com.example.pfe.persistence.entiy.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
@Repository
    public interface StopRepo extends JpaRepository<StopEntity,Integer> {

    @Query("SELECT s  FROM StopEntity s")
    List<StopEntity> getAllStops();

    List<StopEntity> findByStarttime(LocalTime start_time);

}

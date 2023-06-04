package com.example.pfe.persistence.repo;

import com.example.pfe.persistence.entiy.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends JpaRepository <ConfigEntity,Integer> {


}

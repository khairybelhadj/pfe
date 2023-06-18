package com.example.pfe.persistence.repo;

import com.example.pfe.persistence.entiy.ProductEntity;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Integer> {

         ProductEntity findByNomProduit(String productName);
}

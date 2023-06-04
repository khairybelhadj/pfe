package com.example.pfe.controller.dto;


import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.Period;

@Data
public class ProductDto {
    private  Integer id;
    private  String nomProduit;
    private  Period cycleTempsTheorique;
}

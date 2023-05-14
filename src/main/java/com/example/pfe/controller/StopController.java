package com.example.pfe.controller;

import com.example.pfe.entiy.StopEntity;
import com.example.pfe.model.Stop;
import com.example.pfe.model.enumuration.TypesDarrets;
import com.example.pfe.repo.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.*;


@RestController
public class StopController {

    @Autowired
    StopRepo stopRepo;
    static List<Stop> stops;


    @GetMapping("top/get")
    public List<Stop> getStop() {
        return stops;
    }

    @GetMapping("getById/{id}")
    public StopEntity getStopById(@PathVariable Integer id) {
        Optional<StopEntity> stopOpt = stopRepo.findById(id);
        if (stopOpt.isPresent()) {
            StopEntity stopDB = stopOpt.get();
            stopDB.setStart_time(LocalTime.now());
            return stopDB;
        } else {
            return new StopEntity();
        }
    }

    @PostMapping("save")
    public StopEntity savaStop(@RequestBody StopEntity stop) {
        System.out.println(stop);
        stopRepo.save(stop);
        return stop;
    }

    @PutMapping("update")
    public List<Stop> updateStop(@RequestBody Stop newstop) {
        for (int i = 0; i < stops.size(); i++) {
            if (stops.get(i).getId() == newstop.getId()) {
                stops.set(i, newstop);
            }
        }
        return stops;
    }

    @GetMapping("getSalah")
    public ModelAndView raja3Salh() {
        Map model = new HashMap<>();
        model.put("name", "salah");
        ModelAndView modelView = new ModelAndView("sss", model);
        return modelView;
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("numberOfMovies", "1234");
        return new ModelAndView(viewName, model);
    }


    @GetMapping("salah")
    public ModelAndView getSlah(@RequestParam("nameurl") String nameProgram) {
        Map<String, String> model = new HashMap<>();
        model.put("namehtml", nameProgram.toLowerCase());
        return new ModelAndView("watchlist", model);
    }

    @GetMapping("Gestion_d_arrés")
    public ModelAndView getGestion_d_arrés() {
        Map<String, String> model = new HashMap<>();
        model.put("arrets", "Gestion_d_arrés");
        return new ModelAndView("arretsform", model);
    }

    @GetMapping("Gestion_produits")
    public ModelAndView GetGestion_produits() {
        Map<String, String> model = new HashMap<>();
        model.put("namehtml", "Gestion produits");
        return new ModelAndView("prodform", model);
    }

    @GetMapping("Gestion_quatités")
    public ModelAndView GetQuantity() {

        Map<String, String> model = new HashMap<>();
        model.put("namehtml", "Gestion quatités");
        return new ModelAndView("quantform", model);
    }


}


package com.example.pfe.controller;

import com.example.pfe.application.DataBaseConfigService;
import com.example.pfe.controller.dto.StopDto;
import com.example.pfe.controller.dto.WorkPeriodDto;
import com.example.pfe.persistence.entiy.ProductEntity;
import com.example.pfe.persistence.entiy.StopEntity;
import com.example.pfe.model.Stop;
import com.example.pfe.persistence.repo.ProductRepo;
import com.example.pfe.persistence.repo.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class StopController {

    @Autowired
    DataBaseConfigService dataBaseConfigService;


    /**
     * @param workPeriodId
     * @return
     */


    @GetMapping("getStopsByWorkPeriodId")
    public List<StopEntity> getStopsByWorkPeriodId(@RequestParam Integer workPeriodId) {
        return dataBaseConfigService.getStopsByWorkPeriodId(workPeriodId);
    }

    @GetMapping()
    public ModelAndView getIndex() {
        Map<String, Object> model = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        model.put("key1",list1);
        ModelAndView modelAndView = new ModelAndView("index", model);
        return modelAndView;
    }
    @GetMapping("/add/workPeriod")
    public ModelAndView addWorkPeriodGet(){
        Map<String, Object> model = new HashMap<>();
        WorkPeriodDto workPeriodDto=new WorkPeriodDto();
        workPeriodDto.setBottom(false);
        workPeriodDto.setTop(true);
        model.put("workperiod",workPeriodDto);
        ModelAndView modelAndView = new ModelAndView("quantform", model);
        return modelAndView;
    }
    @PostMapping("postWorkPeiod")
    public ModelAndView postWorkPeiod(WorkPeriodDto workPeriodDto){
        System.out.println(workPeriodDto);
        return new ModelAndView();
    }


    @PostMapping("add")
    public List<StopEntity> get(@RequestBody StopDto stopDto) {
        return dataBaseConfigService.saveStop(stopDto);
    }


//    @GetMapping("getById/{id}")
//    public StopEntity getStopById(@PathVariable Integer id) {
//        Optional<StopEntity> stopOpt = stopRepo.findById(id);
//        if (stopOpt.isPresent()) {
//            StopEntity stopDB = stopOpt.get();
//            stopDB.setStarttime(LocalTime.now());
//            return stopDB;
//        } else {
//            return new StopEntity();
//        }
//    }

//    @GetMapping("all")
//    List<StopEntity> getAll() {
//        return stopRepo.findByStarttime(LocalTime.now());
//    }
//
//    @PostMapping("save")
//    public StopEntity savaStop(@RequestBody StopEntity stop) {
//        System.out.println(stop);
//        stopRepo.save(stop);
//        return stop;
//    }
//
//    @PutMapping("update")
//    public List<Stop> updateStop(@RequestBody Stop newstop) {
//        for (int i = 0; i < stops.size(); i++) {
//            if (stops.get(i).getId() == newstop.getId()) {
//                stops.set(i, newstop);
//            }
//        }
//        return stops;
//    }
//
//    @GetMapping("getSalah")
//    public ModelAndView raja3Salh() {
//        Map model = new HashMap<>();
//        model.put("name", "salah");
//        ModelAndView modelView = new ModelAndView("sss", model);
//        return modelView;
//    }
//
//    @GetMapping("/watchlist")
//    public ModelAndView getWatchlist() {
//        String viewName = "watchlist";
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("numberOfMovies", "1234");
//        return new ModelAndView(viewName, model);
//    }
//
//
//    @GetMapping("salah")
//    public ModelAndView getSlah(@RequestParam("nameurl") String nameProgram) {
//        Map<String, String> model = new HashMap<>();
//        model.put("namehtml", nameProgram.toLowerCase());
//        return new ModelAndView("watchlist", model);
//    }
//
//    @GetMapping("Gestion_d_arrés")
//    public ModelAndView getGestion_d_arrés() {
//        Map<String, String> model = new HashMap<>();
//        model.put("arrets", "Gestion_d_arrés");
//        return new ModelAndView("arretsform", model);
//    }
//
//    @GetMapping("Gestion_produits")
//    public ModelAndView GetGestion_produits() {
//        Map<String, String> model = new HashMap<>();
//        model.put("namehtml", "Gestion produits");
//        return new ModelAndView("prodform", model);
//    }
//
//    @GetMapping("Gestion_quatités")
//    public ModelAndView GetQuantity() {
//
//        Map<String, String> model = new HashMap<>();
//        model.put("namehtml", "Gestion quatités");
//        return new ModelAndView("quantform", model);
//    }


}


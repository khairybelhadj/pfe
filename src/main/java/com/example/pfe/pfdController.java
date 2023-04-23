package com.example.pfe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@RestController
public class pfdController {

//    @GetMapping("salah")
//    public ModelAndView raja3Salh() {
//        Map model=new HashMap<>();
//        model.put("name","salah");
//        ModelAndView modelView= new ModelAndView("sss",model);
//        return modelView;
//    }

//    @GetMapping("/watchlist")
//    public ModelAndView getWatchlist() {
//        String viewName = "watchlist";
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("numberOfMovies", "1234");
//        return new ModelAndView(viewName , model);
//    }


    @GetMapping("salah")
    public ModelAndView getSlah(@RequestParam("nameurl") String nameProgram){
      Map<String,String> model=new HashMap<>();
      model.put("namehtml",nameProgram.toLowerCase());
      return new ModelAndView("watchlist",model);
    }
    @GetMapping("Gestion_d_arrés")
    public ModelAndView getGestion_d_arrés(){
        Map<String,String> model=new HashMap<>();
        model.put("arrets","Gestion_d_arrés");
        return new ModelAndView("arretsform",model);
    }
    @GetMapping("Gestion_produits")
    public ModelAndView GetGestion_produits(){
        Map<String,String> model=new HashMap<>();
        model.put("namehtml","Gestion produits");
        return new ModelAndView("prodform",model);
    }
    @GetMapping("Gestion_quatités")
    public ModelAndView GetQuantity(){
        Map<String,String> model=new HashMap<>();
        model.put("namehtml","Gestion quatités");
        return new ModelAndView("quantform",model);
    }


}

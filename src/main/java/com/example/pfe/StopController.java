package com.example.pfe;

import com.example.pfe.model.Stop;
import com.example.pfe.model.TypesDarrets;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class StopController {

    static List<Stop> stops;

    static {
        stops = new ArrayList<>();
        Stop stop = new Stop(TypesDarrets.ArretPlanifieParLaProd,
                LocalTime.now(),
                LocalTime.now().plusHours(2),
                "cause1",
                "desc");
        Stop stop1 = new Stop(TypesDarrets.DemarrageArretDeLigne,
                LocalTime.now(),
                LocalTime.now().plusHours(2),
                "cause2",
                "desc2");
        Stop stop2 = new Stop(TypesDarrets.AttenteManqueKitOF,
                LocalTime.now(),
                LocalTime.now().plusHours(2),
                "cause3",
                "desc3");

        //ADD IN THE LIST
        stops.add(stop);
        stops.add(stop1);
        stops.add(stop2);
    }

    @GetMapping("top/get")
    public List<Stop> getStop() {
        return stops;
    }

    @PostMapping("save")
    public List<Stop> savaStop(@RequestBody Stop stop) {
        System.out.println(stop);
        stops.add(stop);
        return stops;
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

        @GetMapping("salah")
    public ModelAndView raja3Salh() {
        Map model=new HashMap<>();
        model.put("name","salah");
        ModelAndView modelView= new ModelAndView("sss",model);
        return modelView;
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("numberOfMovies", "1234");
        return new ModelAndView(viewName , model);
    }


    @GetMapping("salah")
    public ModelAndView getSlah(@RequestParam("nameurl") String nameProgram) {
        Map<String, String> model = new HashMap<>();
        model.put("namehtml", nameProgram.toLowerCase());
        return new ModelAndView("watchlist", model);
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


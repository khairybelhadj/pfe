package com.example.pfe.controller;

import com.example.pfe.application.DataBaseConfigService;
import com.example.pfe.controller.dto.ProductDto;
import com.example.pfe.controller.dto.StopDto;
import com.example.pfe.controller.dto.WorkPeriodDto;
import com.example.pfe.controller.dto.WorkerDto;
import com.example.pfe.persistence.entiy.StopEntity;
import com.example.pfe.persistence.repo.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class AppController {

    @Autowired
    DataBaseConfigService dataBaseConfigService;



//    @GetMapping("getStopsByWorkPeriodId")
//    public List<StopEntity> getStopsByWorkPeriodId(@RequestParam Integer workPeriodId) {
//        return dataBaseConfigService.getStopsByWorkPeriodId(workPeriodId);
//    }
    @GetMapping("test")
    public ModelAndView getAll() {
        List<WorkPeriodDto> WorkPeriodDtos = dataBaseConfigService.getTodayWorkPeriod();
         WorkPeriodDtos.stream().forEach(workPeriodDto -> {
             workPeriodDto.setStopDtos(dataBaseConfigService.getStopsByWorkPeriodId(workPeriodDto.getId()));
        });
        Map<String, Object> model = new HashMap<>();
        model.put("workPeriodLIST",WorkPeriodDtos);

        return new ModelAndView("prodform",model);
    }

    @GetMapping("login")
    public ModelAndView login() {
        Map<String, Object> model = new HashMap<>();

        WorkerDto workerDto = new WorkerDto();

        model.put("woker", workerDto);
        // creat the modelAndView
        ModelAndView modelAndView = new ModelAndView("login", model);
        return modelAndView;
    }

    @PostMapping("postLogin")
    public ModelAndView postlogin(WorkerDto workerDto, HttpSession session) {
        Map<String, Object> model = new HashMap<>();
        // Redirect to the index view
        RedirectView redirectView = new RedirectView();
        if (dataBaseConfigService.verifie(workerDto)) {
            session.setAttribute("WorkerName", workerDto.getWorkerName());
            session.setAttribute("WorkerTeams", workerDto.getTeam());
            if (dataBaseConfigService.isAdmin(workerDto.getWorkerName())) {
                session.setAttribute("WorkerType", "Admin");
            } else {
                session.setAttribute("WorkerType", "Worker");
            }
            redirectView.setUrl("/");

        } else {
            redirectView.setUrl("/login");

        }
        return new ModelAndView(redirectView);
    }

    @GetMapping("Deconnexion")
    public ModelAndView Deconnexion(HttpSession session) {
        RedirectView redirectView = new RedirectView();
        session.setAttribute("WorkerName", null);
        session.setAttribute("WorkerTeams", null);
        redirectView.setUrl("/login");
        return new ModelAndView(redirectView);
    }

    @GetMapping()
    public ModelAndView getIndex(HttpSession session) {

        String name = (String) session.getAttribute("WorkerName");
        System.out.println(name);
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }
        // create the model
        Map<String, Object> model = new HashMap<>();

        // get the list od the today work period and put it into the model
        List<WorkPeriodDto> list1 = dataBaseConfigService.getTodayWorkPeriod();
        model.put("workPeriodLIST", list1);

        // put the today date in the model
        model.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));

        // creat the modelAndView
        ModelAndView modelAndView = new ModelAndView("index", model);
        return modelAndView;
    }

    @GetMapping("/add/workPeriod")
    public ModelAndView addWorkPeriodGet(HttpSession session) {

        String name = (String) session.getAttribute("WorkerName");
        System.out.println(name);
        if (name == null) {
            RedirectView redirectView = new RedirectView("/login");
            return new ModelAndView(redirectView);
        }

        Map<String, Object> model = new HashMap<>();
        WorkPeriodDto workPeriodDto = new WorkPeriodDto();
        workPeriodDto.setBottom(false);
        workPeriodDto.setTop(true);
        List<String> productNameList = dataBaseConfigService.getProductNames();
        model.put("productNameList", productNameList);
        model.put("workperiod", workPeriodDto);
        ModelAndView modelAndView = new ModelAndView("quantform", model);
        return modelAndView;
    }

//    @GetMapping("/add/product")
//    public ModelAndView addProductGet() {
//        Map<String, Object> model = new HashMap<>();
//        ProductDto productDto = new ProductDto();
//        model.put("productDto", productDto);
//        ModelAndView modelAndView = new ModelAndView("productForm", model);
//        return modelAndView;
//    }


    @GetMapping("/Ajouter_Operateur")
    public ModelAndView AddWorkerGet(HttpSession session) {
        String name = (String) session.getAttribute("WorkerName");
        String type = (String) session.getAttribute("WorkerType");
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }
        if (!type.equals("Admin")) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/");
            return new ModelAndView(redirectView);
        }
        Map<String, Object> model = new HashMap<>();
        WorkerDto workerDto = new WorkerDto();
        model.put("workerDto", workerDto);
        List<WorkerDto> list1 = dataBaseConfigService.getAllWorker();
        model.put("workerLIST", list1);
        ModelAndView modelAndView = new ModelAndView("AddWorker", model);
        return modelAndView;
    }

    @GetMapping("/Gestion_d_Arréts")
    public ModelAndView addstopsGet(HttpSession session) {

        String name = (String) session.getAttribute("WorkerName");
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }
        Map<String, Object> model = new HashMap<>();
        StopDto stopDto = new StopDto();
        model.put("stopDto", stopDto);
        ModelAndView modelAndView = new ModelAndView("arretsform", model);
        return modelAndView;
    }

    @GetMapping("/Gestion_Quatités")
    public ModelAndView addQuantGet(HttpSession session) {
        String name = (String) session.getAttribute("WorkerName");
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }
        Map<String, Object> model = new HashMap<>();
        ProductDto productDto = new ProductDto();
        model.put("productDto", productDto);
        ModelAndView modelAndView = new ModelAndView("quantform", model);
        return modelAndView;
    }

    @PostMapping("postWorker")
    public ModelAndView postWorker(WorkerDto workerdDto) {

        // Print the new Work Period
        System.out.println(workerdDto);

        //save the new Work period
        dataBaseConfigService.saveWorker(workerdDto);

        // Redirect to the index view
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Ajouter_Operateur");
        return new ModelAndView(redirectView);
    }

    @GetMapping("/Ajouter_Produit")
    public ModelAndView AddProductGet(HttpSession session) {


        String name = (String) session.getAttribute("WorkerName");
        String type = (String) session.getAttribute("WorkerType");
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }
        if (!type.equals("Admin")) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/");
            return new ModelAndView(redirectView);
        }

        Map<String, Object> model = new HashMap<>();
        ProductDto productDto = new ProductDto();
        model.put("ProductDto", productDto);
        List<ProductDto> list1 = dataBaseConfigService.getAllProduct();
        model.put("productLIST", list1);
        ModelAndView modelAndView = new ModelAndView("AddProduct", model);
        return modelAndView;
    }

    @PostMapping("postProduct")
    public ModelAndView postProduct(ProductDto productDto) {

        // Print the new Work Period
        System.out.println(productDto);

        //save the new Work period
        dataBaseConfigService.saveProduct(productDto);
        // Redirect to the add product view
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/Ajouter_Produit");
        return new ModelAndView(redirectView);
    }

    @PostMapping("postWorkPeiod")
    public ModelAndView postWorkPeiod(WorkPeriodDto workPeriodDto) {

        // Print the new Work Period
        System.out.println(workPeriodDto);

        //save the new Work period
        dataBaseConfigService.saveWorkPeriod(workPeriodDto);

        // Redirect to the index view
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);
    }

    @GetMapping("/add/stop")
    public ModelAndView addStopGet(@RequestParam Integer workPeroiodId, HttpSession session) {
        String name = (String) session.getAttribute("WorkerName");
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }

        Map<String, Object> model = new HashMap<>();
        StopDto stopDto = new StopDto();
        stopDto.setWorkPeriodId(workPeroiodId);
        model.put("stopDto", stopDto);
        ModelAndView modelAndView = new ModelAndView("arretsform", model);
        return modelAndView;
    }

    @PostMapping("postStop")
    public ModelAndView get(StopDto stopDto) {
        System.out.println(stopDto);
        dataBaseConfigService.saveStop(stopDto);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);

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
//      @GetMapping("Gestion_d_arrés")
//       public ModelAndView getGestion_d_arrés() {
//      Map<String, String> model = new HashMap<>();
//  model.put("arrets", "Gestion_d_arrés");
//return new ModelAndView("arretsform", model); //}
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


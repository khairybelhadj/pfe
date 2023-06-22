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
import org.springframework.ui.Model;
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
    @GetMapping("look")
    public ModelAndView getAll() {
        String shift1 = "Equipe 1";
        String shift2 = "Equipe 2";
        String shift3 = "Equipe 3";

        List<WorkPeriodDto> WorkPeriodDtos = dataBaseConfigService.getTodayWorkPeriod(shift1);
        WorkPeriodDtos.stream().forEach(workPeriodDto -> {
            workPeriodDto.setStopDtos(dataBaseConfigService.getStopsByWorkPeriodId(workPeriodDto.getId()));
        });
        List<WorkPeriodDto> WorkPeriodDtos1 = dataBaseConfigService.getTodayWorkPeriod(shift2);
        WorkPeriodDtos.stream().forEach(workPeriodDto -> {
            workPeriodDto.setStopDtos(dataBaseConfigService.getStopsByWorkPeriodId(workPeriodDto.getId()));
        });
        List<WorkPeriodDto> WorkPeriodDtos2 = dataBaseConfigService.getTodayWorkPeriod(shift3);
        WorkPeriodDtos.stream().forEach(workPeriodDto -> {
            workPeriodDto.setStopDtos(dataBaseConfigService.getStopsByWorkPeriodId(workPeriodDto.getId()));
        });
        Map<String, Object> model = new HashMap<>();
        model.put("workPeriodLIST", WorkPeriodDtos);
        model.put("workPeriodLIST1", WorkPeriodDtos1);
        model.put("workPeriodLIST2", WorkPeriodDtos2);

        return new ModelAndView("prodform", model);
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
        String team = (String) session.getAttribute("WorkerTeams");

        System.out.println(name);
        if (name == null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("login");
            return new ModelAndView(redirectView);
        }
        // create the model
        Map<String, Object> model = new HashMap<>();

        // get the list od the today work period and put it into the model
        List<WorkPeriodDto> list1 = dataBaseConfigService.getTodayWorkPeriod(team);
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

    @GetMapping("/validate")
    public ModelAndView updateWorkPeriodGet(@RequestParam("workPeroiodId") Integer workPeroiodId, HttpSession session) {

        String name = (String) session.getAttribute("WorkerName");
        System.out.println(name);
        if (name == null) {
            RedirectView redirectView = new RedirectView("/login");
            return new ModelAndView(redirectView);
        }

        Map<String, Object> model = new HashMap<>();
        WorkPeriodDto workPeriodDto = dataBaseConfigService.getWorkPeriodById(workPeroiodId);
        model.put("workperiod", workPeriodDto);
        ModelAndView modelAndView = new ModelAndView("update", model);
        return modelAndView;
    }

    @PostMapping("validate")
    public ModelAndView postvalidate(WorkPeriodDto workPeriodDto) {


        //save the new Work period
        dataBaseConfigService.validate_workPeriod(workPeriodDto);

        // Redirect to the index view
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);
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

    /**
     * @param stopDto
     * @return
     */
    @PostMapping("postStop")
    public ModelAndView get(StopDto stopDto) {
        System.out.println(stopDto);
        dataBaseConfigService.saveStop(stopDto);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return new ModelAndView(redirectView);

    }

    @GetMapping("print")
    public ModelAndView getGraph(@RequestParam("shift") String shift) {

        // Calcule
        var time = dataBaseConfigService.calculate(LocalDate.now(),shift );

      // affichage
        Map<String,Object> model= new HashMap<>();
        /// ooe
        Map<String, Long> graphDataTeams1 = new TreeMap<>();
        graphDataTeams1.put("WorkTime", time.get("ooe"));
        graphDataTeams1.put("RestTile", 100- time.get("ooe"));
        model.put("chartDataOOE", graphDataTeams1);


        /// TRG
        Map<String, Long> graphDataTeams2 = new TreeMap<>();
        graphDataTeams2.put("WorkTime", time.get("trg"));
        graphDataTeams2.put("RestTile", 100- time.get("trg"));
        model.put("chartDataTRG", graphDataTeams2);



        /// TRE
        Map<String, Long> graphDataTeams3 = new TreeMap<>();
        graphDataTeams3.put("WorkTime", time.get("tre"));
        graphDataTeams3.put("RestTile", 100- time.get("tre"));
        model.put("chartDataTRE", graphDataTeams3);
        model.put("team",shift);
        return new ModelAndView("print",model);
    }
}






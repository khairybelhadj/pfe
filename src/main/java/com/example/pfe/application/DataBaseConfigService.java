package com.example.pfe.application;

import com.example.pfe.controller.dto.ConfigDto;
import com.example.pfe.controller.dto.ProductDto;
import com.example.pfe.controller.dto.StopDto;
import com.example.pfe.controller.dto.WorkPeriodDto;
import com.example.pfe.model.enumuration.Jour;
import com.example.pfe.persistence.entiy.*;
import com.example.pfe.persistence.repo.ConfigRepo;
import com.example.pfe.persistence.repo.ProductRepo;
import com.example.pfe.persistence.repo.StopRepo;
import com.example.pfe.persistence.repo.WorkPeriodRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.DayOfWeek.SUNDAY;

@Service
public class DataBaseConfigService {
    /**
     *
     */
    @Autowired
    private StopRepo stopRepo;

    /**
     *
     */
    @Autowired
    private ProductRepo productRepo;

    /**
     *
     */
    @Autowired
    private WorkPeriodRepo workPeriodRepo;
    @Autowired
    private ConfigRepo configRepo;

    public StopEntity createStop(StopEntity stop) {

        return stopRepo.save(stop);
    }

    public List<StopEntity> saveStop(@NotNull StopDto stop) {
        Optional<WorkPeriodEntity> workPeriodEntityOp = workPeriodRepo.findById(stop.getWorkPeriodId());
        if (workPeriodEntityOp.isPresent()) {
            WorkPeriodEntity workPeriodEntityDB = workPeriodEntityOp.get();
            workPeriodEntityDB.getStopEntities().add(new StopEntity(stop));
            workPeriodRepo.save(workPeriodEntityDB);
            return workPeriodEntityDB.getStopEntities();
        } else throw new RuntimeException();
    }

    /**
     * @param workPeriodId
     * @return
     */
    public @NotNull List<StopEntity> getStopsByWorkPeriodId(@NotNull Integer workPeriodId) {
        List<StopEntity> stopEntities;
        Optional<WorkPeriodEntity> workPeriodEntityOp = workPeriodRepo.findById(workPeriodId);

        if (workPeriodEntityOp.isPresent()) {
            stopEntities = workPeriodEntityOp.get().getStopEntities();
        } else {
            stopEntities = new ArrayList<>();
        }
        return stopEntities;

    }
    public @NotNull ConfigDto saveConfiguration (@NotNull ConfigDto configDto){
        // create the new configuration
        ConfigEntity newConfigEntity = new ConfigEntity();
        BeanUtils.copyProperties(configDto,newConfigEntity);
        // save configuration
        configRepo.save(newConfigEntity);
        return null ;
    }


    /**
     * @param workPeriodDto
     * @return
     */
    public @NotNull List<WorkPeriodDto> saveWorkPeriod(@NotNull WorkPeriodDto workPeriodDto) {

        // create the new work period
        WorkPeriodEntity newWorkPeriodEntity = new WorkPeriodEntity();
        BeanUtils.copyProperties(workPeriodDto, newWorkPeriodEntity);

        // Get Today  as a Jour Enum
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        int dayNumber = today.getValue();
        Jour jour = Jour.of(dayNumber);
        newWorkPeriodEntity.setJour(jour);


        // Get the product associated with the new work Period
        ProductEntity productEntityDB = productRepo.findById(workPeriodDto.getProductId()).get();
        newWorkPeriodEntity.setProductEntity(productEntityDB);
        newWorkPeriodEntity.setDate(LocalDate.now());
        newWorkPeriodEntity.setStartTime(LocalTime.parse((CharSequence) workPeriodDto.getStartTime(),DateTimeFormatter.ofPattern("HH:mm")));

        // save the work period
        workPeriodRepo.save(newWorkPeriodEntity);

        // Get the today work period
        List<WorkPeriodEntity> works = workPeriodRepo.findByJour(jour);

        return works.stream().map(workPeriodEntity -> {
            WorkPeriodDto workPeriodDto1 = new WorkPeriodDto();
            BeanUtils.copyProperties(workPeriodEntity, workPeriodDto1);
            return workPeriodDto1;
        }).toList();
    }

    /**
     * @return
     */
    public List<WorkPeriodDto> getTodayWorkPeriod(){

        // get the list of entity from database
        List<WorkPeriodEntity> works = workPeriodRepo.findByDate(LocalDate.now());

        // Convert entity to dto
        return works.stream().map(workPeriodEntity -> {
            WorkPeriodDto workPeriodDto1 = new WorkPeriodDto();
            BeanUtils.copyProperties(workPeriodEntity, workPeriodDto1);
            workPeriodDto1.setProductId(workPeriodEntity.getProductEntity().getId());
            if(null!=workPeriodEntity.getStartTime()){
                workPeriodDto1.setStartTime(workPeriodEntity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            return workPeriodDto1;
        }).toList();
    }

    public @NotNull ProductDto saveProduct(@NotNull ProductDto productDto){
        // create the new product
        ProductEntity newProductEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, newProductEntity);

        // save the product
        ProductEntity productEntity= productRepo.save(newProductEntity);

        // create the new product
        ProductDto newProductDTO = new ProductDto();
        BeanUtils.copyProperties(productEntity, newProductDTO);

        return  newProductDTO;


    }
}

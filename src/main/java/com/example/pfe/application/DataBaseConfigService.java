package com.example.pfe.application;

import com.example.pfe.controller.dto.StopDto;
import com.example.pfe.persistence.entiy.StopEntity;
import com.example.pfe.persistence.entiy.WorkPeriodEntity;
import com.example.pfe.persistence.entiy.WorkerEntity;
import com.example.pfe.persistence.repo.ProductRepo;
import com.example.pfe.persistence.repo.StopRepo;
import com.example.pfe.persistence.repo.WorkPeriodRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataBaseConfigService {
    @Autowired
    private StopRepo stopRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private WorkPeriodRepo workPeriodRepo;


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

    public WorkPeriodEntity saveWorkPeriod(@NotNull WorkPeriodEntity workPeriodEntity) {
            return null;
    }
}

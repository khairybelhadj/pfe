package com.example.pfe.application;

import com.example.pfe.controller.dto.ProductDto;
import com.example.pfe.controller.dto.StopDto;
import com.example.pfe.controller.dto.WorkPeriodDto;
import com.example.pfe.controller.dto.WorkerDto;
import com.example.pfe.model.enumuration.CategorieDarrets;
import com.example.pfe.model.enumuration.Jour;
import com.example.pfe.persistence.entiy.*;
import com.example.pfe.persistence.repo.*;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private WorkerRepo workerRepo;

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
    public @NotNull List<StopDto> getStopsByWorkPeriodId(@NotNull Integer workPeriodId) {
        List<StopDto> StopDtos;
        Optional<WorkPeriodEntity> workPeriodEntityOp = workPeriodRepo.findById(workPeriodId);

        if (workPeriodEntityOp.isPresent()) {
            StopDtos = workPeriodEntityOp.get().getStopEntities().stream().map(stopEntity -> {
                StopDto newStopDto = new StopDto();
                BeanUtils.copyProperties(stopEntity, newStopDto);
                newStopDto.setEnd_time(stopEntity.getEnd_time().toString());
                newStopDto.setStarttime(stopEntity.getStarttime().toString());
                newStopDto.setCategorieDarrets(stopEntity.getCategorieDarrets());
                newStopDto.setTypesDarrets(stopEntity.getTypesDarrets().toString());
                return newStopDto;
            }).toList();
        } else {
            StopDtos = new ArrayList<>();
        }
        return StopDtos;

    }

    public void saveWorker(@NotNull WorkerDto workerDto) {

        // create the new Worker
        WorkerEntity newWorkerEntity = new WorkerEntity();

        // Copy the properties
        BeanUtils.copyProperties(workerDto, newWorkerEntity);

        // save Worker
        workerRepo.save(newWorkerEntity);

//        workerDtos.forEach(workerDtoVar -> System.out.println(workerDtoVar));

    }

    public void saveProduct(@NotNull ProductDto productDto) {

        ProductEntity newProductEntity = new ProductEntity();

        BeanUtils.copyProperties(productDto, newProductEntity);

        productRepo.save(newProductEntity);

        List<ProductDto> productDtos = productRepo.findAll().stream().map(productEntity -> {
            ProductDto tmpProductDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, tmpProductDto);
            return tmpProductDto;
        }).toList();
    }

    public Boolean verifie(WorkerDto workerDto) {
        List<WorkerEntity> workerEntities = workerRepo.findByworkerName(workerDto.getWorkerName());
        Optional isPresent = workerEntities.stream().map(WorkerEntity::getPassword).filter(password -> password.equals(workerDto.getPassword())).findAny();
        if (isPresent.isPresent()) return true;
        else return false;
    }

    public Boolean isAdmin(String name) {
        List<WorkerEntity> workerEntities = workerRepo.findByworkerName(name);
        Optional isAdmin = workerEntities.stream().map(WorkerEntity::getType).filter(obj -> obj.equals("Admin")).findAny();
        return isAdmin.isPresent();
    }

    public List<WorkerDto> getAllWorker() {
        // Convert the Entity to dto
        List<WorkerDto> workerDtos = workerRepo.findAll().stream().map(workerEntity -> {
            WorkerDto tmpWorkerDto = new WorkerDto();
            BeanUtils.copyProperties(workerEntity, tmpWorkerDto);
            tmpWorkerDto.setWorkerId(workerEntity.getId());
            return tmpWorkerDto;
        }).toList();
        workerDtos.forEach(System.out::println);
        return workerDtos;

    }

    public List<ProductDto> getAllProduct() {
        // Convert the Entity to dto
        List<ProductDto> productDtos = productRepo.findAll().stream().map(productEntity -> {
            ProductDto tmpProductDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, tmpProductDto);
            tmpProductDto.setNomProduit(productEntity.getNomProduit());
            return tmpProductDto;
        }).toList();
        productDtos.forEach(System.out::println);
        return productDtos;

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
        ProductEntity productEntityDB = productRepo.findByNomProduit(workPeriodDto.getNomProduit());
        newWorkPeriodEntity.setProductEntity(Optional.of(productEntityDB).get());
        newWorkPeriodEntity.setDate(LocalDate.now());
        newWorkPeriodEntity.setStartTime(LocalTime.parse((CharSequence) workPeriodDto.getStartTime(), DateTimeFormatter.ofPattern("HH:mm")));

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

    @Transactional
    public void validate_workPeriod(WorkPeriodDto workPeriodDto) {
        WorkPeriodEntity workPeriodEntity = workPeriodRepo.getById(workPeriodDto.getId());
        Integer id = workPeriodEntity.getId();
        BeanUtils.copyProperties(workPeriodDto, workPeriodEntity);
        workPeriodEntity.setEndTime(LocalTime.parse(workPeriodDto.getEndTime()));
        workPeriodEntity.setStartTime(LocalTime.parse(workPeriodDto.getStartTime()));
        workPeriodEntity.setId(id);
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        int dayNumber = today.getValue();
        Jour jour = Jour.of(dayNumber);
        workPeriodEntity.setJour(jour);
    }

    /**
     * @return
     */
    public List<WorkPeriodDto> getTodayWorkPeriod(String team) {

        // get the list of entity from database
        List<WorkPeriodEntity> works = workPeriodRepo.findByDate(LocalDate.now()).stream().filter(workPeriodEntity -> workPeriodEntity.getShift().equals(team)).toList();


        // Convert entity to dto
        return works.stream().map(workPeriodEntity -> {
            WorkPeriodDto workPeriodDto1 = new WorkPeriodDto();
            BeanUtils.copyProperties(workPeriodEntity, workPeriodDto1);
            Integer stopCount = (workPeriodEntity.getStopEntities().size());
            workPeriodDto1.setStopCount(stopCount);
            workPeriodDto1.setProductId(workPeriodEntity.getProductEntity().getId());
            workPeriodDto1.setNomProduit(workPeriodEntity.getProductEntity().getNomProduit());
            if (null != workPeriodEntity.getStartTime()) {
                workPeriodDto1.setStartTime(workPeriodEntity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            return workPeriodDto1;
        }).toList();
    }

    public List<String> getProductNames() {
        List<String> productNameList = productRepo.findAll().stream().map(ProductEntity::getNomProduit).toList();
        return productNameList;
    }

//    public @NotNull ProductDto saveProduct(@NotNull ProductDto productDto) {
//        // create the new product
//        ProductEntity newProductEntity = new ProductEntity();
//        BeanUtils.copyProperties(productDto, newProductEntity);
//
//        // save the product
//        ProductEntity productEntity = productRepo.save(newProductEntity);
//
//        // create the new product
//        ProductDto newProductDTO = new ProductDto();
//        BeanUtils.copyProperties(productEntity, newProductDTO);
//
//        return newProductDTO;
//
//
//    }

    public Map<String, Long> calculate(LocalDate date, String shift) {

        List<WorkPeriodEntity> workperiodList = workPeriodRepo.findByDate(date).stream().
                filter(workPeriodEntity -> workPeriodEntity.getShift().equals(shift)).
                toList();
        Map<String, Long> result = new HashMap<>();

        if (workperiodList.isEmpty()){
            result.put("ooe",0L);
            result.put("tre",0L);
            result.put("trg",0L);

        }

        List<WorkPeriodEntity> workperiodListFt = workperiodList.stream().filter(workPeriodEntity -> workPeriodEntity.getShift().equals(shift)).toList();

        // Calculate the min of the local time
        LocalTime localTimeMin = workperiodListFt.stream().map(WorkPeriodEntity::getStartTime).filter(Objects::nonNull).sorted().findFirst().get();

        // fing the 8 hours work periode
        long temp8 = Duration.between(localTimeMin, LocalTime.now()).toMinutes();
        if (temp8 > 480) {
            temp8 = 480;
        }
//        long temp8 = workperiodList.stream().map(workPeriodEntity -> {
//            return Duration.between(workPeriodEntity.getEndTime(), workPeriodEntity.getStartTime());})
//                    .map(Duration::abs).reduce(Duration.ZERO, Duration::plus).toMinutes();

        List<StopEntity> stopDtos = workperiodListFt.stream().map(WorkPeriodEntity::getStopEntities).flatMap(List::stream).toList();

        long arretProgTime = stopDtos.stream().
                filter(stopDto -> stopDto.getCategorieDarrets().equals(CategorieDarrets.ARRETPROGRAMMES))
                .map(stopDto -> {
                    return Duration.between(stopDto.getEnd_time(), stopDto.getStarttime());
                }).map(Duration::abs).reduce(Duration.ZERO, Duration::plus).toMinutes();

        long arrettotal = stopDtos.stream().map(stopDto -> {
            return Duration.between(stopDto.getEnd_time(), stopDto.getStarttime());
        }).map(Duration::abs).reduce(Duration.ZERO, Duration::plus).toMinutes();


        double var2 = (double) (temp8 - arretProgTime);
        double var1 = (double) (temp8 - arrettotal);

        var trs = (((double) var1 / (double) var2) * 100);
        result.put("ooe", Math.abs(Math.round(trs)));
        var trg = (var1 / (double) temp8)*100;
        result.put("trg", Math.abs(Math.round(trg)));
        var tre= (trg*(8D/24D));
        result.put("tre", Math.abs(Math.round(tre)));
        return result;
    }

    public WorkPeriodDto getWorkPeriodById(Integer workPeroiodId) {
        return workPeriodRepo.findById(workPeroiodId).map(workPeriodEntity -> {
            WorkPeriodDto workPeriodDto1 = new WorkPeriodDto();
            BeanUtils.copyProperties(workPeriodEntity, workPeriodDto1);
            Integer stopCount = (workPeriodEntity.getStopEntities().size());
            workPeriodDto1.setStopCount(stopCount);
            workPeriodDto1.setProductId(workPeriodEntity.getProductEntity().getId());
            workPeriodDto1.setNomProduit(workPeriodEntity.getProductEntity().getNomProduit());
            if (null != workPeriodEntity.getEndTime()) {
                workPeriodDto1.setEndTime(workPeriodEntity.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            if (null != workPeriodEntity.getStartTime()) {
                workPeriodDto1.setStartTime(workPeriodEntity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            return workPeriodDto1;
        }).get();
    }
}

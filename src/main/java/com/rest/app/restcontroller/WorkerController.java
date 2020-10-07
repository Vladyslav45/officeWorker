package com.rest.app.restcontroller;

import com.rest.app.DTO.WorkerDTO;
import com.rest.app.model.Worker;
import com.rest.app.model.Position;
import com.rest.app.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/office/")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("addWorker")
    public ResponseEntity addWorker(@RequestBody WorkerDTO workerDTO, @RequestParam Position position){
        workerService.addNewWorker(workerDTO, position);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("showAllPositionsWithNumbersWorkers")
    public ResponseEntity<Map<Position, Long>> showAllPositionsWithNumbersWorkers(){
        return new ResponseEntity<>(workerService.showAllPositionWithNumbersWorkers(), HttpStatus.OK);
    }

    @GetMapping("showAllFilteredWorkers")
    public ResponseEntity<List<Worker>> showAllFilteredWorkers(@RequestParam String param){
        return new ResponseEntity<>(workerService.showAllWorkersFilteredByNameOrSurnameOrEmail(param), HttpStatus.OK);
    }

    @DeleteMapping("deleteById")
    public ResponseEntity deleteWorkerById(@RequestParam UUID workerId){
        Worker worker = workerService.findById(workerId);
        if (worker != null){
            workerService.deleteWorker(workerId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

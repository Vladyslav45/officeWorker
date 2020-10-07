package com.rest.app.service;

import com.rest.app.DTO.WorkerDTO;
import com.rest.app.model.Worker;
import com.rest.app.model.Position;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WorkerService {
    Worker findById(UUID id);
    void addNewWorker(WorkerDTO workerDTO, Position position);
    List<Worker> showAllWorkersFilteredByNameOrSurnameOrEmail(String param);
    Map<Position, Long> showAllPositionWithNumbersWorkers();
    void deleteWorker(UUID employerId);
}

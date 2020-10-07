package com.rest.app.service.impl;

import com.rest.app.DTO.WorkerDTO;
import com.rest.app.model.Worker;
import com.rest.app.model.Position;
import com.rest.app.repository.WorkerRepository;
import com.rest.app.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker findById(UUID id) {
       return workerRepository.findById(id).orElse(null);
    }

    @Override
    public void addNewWorker(WorkerDTO workerDTO, Position position) {
        Worker worker = new Worker();
        worker.setName(workerDTO.getName());
        worker.setSurname(workerDTO.getSurname());
        worker.setEmail(workerDTO.getEmail());
        worker.setPosition(position);
        workerRepository.save(worker);
    }

    @Override
    public List<Worker> showAllWorkersFilteredByNameOrSurnameOrEmail(String param) {
        return workerRepository.findAll().stream().filter(worker -> worker.getName().equals(param) || worker.getSurname().equals(param) || worker.getEmail().equals(param)).collect(Collectors.toList());
    }

    @Override
    public Map<Position, Long> showAllPositionWithNumbersWorkers() {
        Map<Position, Long> result = new HashMap<>();
        workerRepository.findAll().forEach(worker -> result.put(worker.getPosition(), workerRepository.findAll().stream().filter(e -> worker.getPosition().equals(e.getPosition())).count()));
        return result;
    }

    @Override
    public void deleteWorker(UUID employerId) {
        workerRepository.findById(employerId).ifPresent(workerRepository::delete);
    }
}

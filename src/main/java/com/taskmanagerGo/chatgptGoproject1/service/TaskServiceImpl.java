package com.taskmanagerGo.chatgptGoproject1.service;

import com.taskmanagerGo.chatgptGoproject1.dto.EmployeeDto;
import com.taskmanagerGo.chatgptGoproject1.model.Task;
import com.taskmanagerGo.chatgptGoproject1.repository.TaskRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo repository;

    public TaskServiceImpl(TaskRepo repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {

        // DTO -> Entity (manual conversion)
        Task entity = new Task();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        // salary & department can be null for now

        // Save to DB
        Task saved = repository.save(entity);

        // Entity -> DTO (manual conversion)
        return new EmployeeDto(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail()
        );
    }



    @Override
    public EmployeeDto getEmployeeById(Long id) {

        Optional<Task> opt = repository.findById(id);

        Task entity = opt.orElseThrow(
                () -> new RuntimeException("Employee not found with id: " + id)
        );

        // Entity -> DTO (manual conversion)
        return new EmployeeDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Task> list = repository.findAll();
        List<EmployeeDto> dtoList = new ArrayList<>();

        for (Task e : list) {
            dtoList.add(new EmployeeDto(
                    e.getId(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getEmail()
            ));
        }

        return dtoList;
    }

}
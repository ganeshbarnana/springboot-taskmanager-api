package com.taskmanagerGo.chatgptGoproject1.controller;

import com.taskmanagerGo.chatgptGoproject1.dto.EmployeeDto;
import com.taskmanagerGo.chatgptGoproject1.model.Task;
import com.taskmanagerGo.chatgptGoproject1.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class TaskManagerController {

    private final TaskService service;



    public TaskManagerController(TaskService service) {
        this.service = service;
    }

    // CREATE EMPLOYEE
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto dto) {
        EmployeeDto created = service.createEmployee(dto);
        return ResponseEntity.status(201).body(created);
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto dto = service.getEmployeeById(id);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return service.getAllEmployees();
    }

}
package com.taskmanagerGo.chatgptGoproject1.service;

import com.taskmanagerGo.chatgptGoproject1.dto.EmployeeDto;

import java.util.List;

public interface TaskService {

    // create a new employee
    EmployeeDto createEmployee(EmployeeDto dto);
    List<EmployeeDto> getAllEmployees();

    // get employee by id
    EmployeeDto getEmployeeById(Long id);
}

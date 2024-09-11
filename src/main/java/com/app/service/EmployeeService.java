package com.app.service;

import com.app.dto.EmployeeRequestDTO;
import com.app.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDTO);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO);
    List<EmployeeResponseDTO> getAllEmployees();
    List<EmployeeResponseDTO> getEmployeesByDepartmentId(Long departmentId);
    
}

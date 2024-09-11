package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.DepartmentRepository;
import com.app.dao.EmployeeRepository;
import com.app.dto.EmployeeRequestDTO;
import com.app.dto.EmployeeResponseDTO;
import com.app.entities.Department;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	 @Autowired
    private EmployeeRepository employeeRepository;
	 @Autowired
	    private DepartmentRepository departmentRepository;
	 @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDTO) {
    	
    	Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = modelMapper.map(requestDTO, Employee.class);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        
        return modelMapper.map(savedEmployee, EmployeeResponseDTO.class);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        modelMapper.map(requestDTO, employee);

        // Fetch the new department by its ID and set it to the employee
        Department newDepartment = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        employee.setDepartment(newDepartment);

        employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }
    
    
    @Override
    public List<EmployeeResponseDTO> getEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }
}

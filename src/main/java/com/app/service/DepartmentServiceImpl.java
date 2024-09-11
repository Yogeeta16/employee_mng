package com.app.service;

import com.app.dao.DepartmentRepository;
import com.app.dto.DepartmentRequestDTO;
import com.app.dto.DepartmentResponseDTO;
import com.app.entities.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentResponseDTO addDepartment(DepartmentRequestDTO requestDTO) {
        Department department = modelMapper.map(requestDTO, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentResponseDTO.class);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        modelMapper.map(requestDTO, department);
        departmentRepository.save(department);
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    @Override
    public List<DepartmentResponseDTO> findDepartmentsByName(String name) {
        List<Department> departments = departmentRepository.findByName(name);
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentResponseDTO.class))
                .collect(Collectors.toList());
    }
}

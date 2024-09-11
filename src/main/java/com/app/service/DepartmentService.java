package com.app.service;

import com.app.dto.DepartmentRequestDTO;
import com.app.dto.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDTO addDepartment(DepartmentRequestDTO requestDTO);
    DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO);
    List<DepartmentResponseDTO> findDepartmentsByName(String name);
}

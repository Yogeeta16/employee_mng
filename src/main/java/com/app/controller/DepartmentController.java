package com.app.controller;

import com.app.dto.DepartmentRequestDTO;
import com.app.dto.DepartmentResponseDTO;
import com.app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> addDepartment(@Validated @RequestBody DepartmentRequestDTO requestDTO) {
        DepartmentResponseDTO responseDTO = departmentService.addDepartment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Long id, @Validated @RequestBody DepartmentRequestDTO requestDTO) {
        DepartmentResponseDTO responseDTO = departmentService.updateDepartment(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DepartmentResponseDTO>> findDepartmentsByName(@RequestParam String name) {
        List<DepartmentResponseDTO> responseDTOs = departmentService.findDepartmentsByName(name);
        return ResponseEntity.ok(responseDTOs);
    }
}

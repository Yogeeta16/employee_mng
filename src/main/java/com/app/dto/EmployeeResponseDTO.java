package com.app.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Date dob;
    private DepartmentResponseDTO department;
}

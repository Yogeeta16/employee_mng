package com.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private String description;
}

package com.aluracursos.LiterAlura.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    
}

package com.aluracursos.LiterAlura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Libro {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private int descarga;

    @ManyToOne
    private Autor autor;
}

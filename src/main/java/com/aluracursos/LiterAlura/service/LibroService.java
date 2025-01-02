package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    public Libro guardarLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public List<Libro> listarLibros(){
        return libroRepository.findAll();
    }
    public List<Libro> listarLibrosPorIdioma(String idioma){
        return libroRepository.findByIdioma(idioma);
    }
}

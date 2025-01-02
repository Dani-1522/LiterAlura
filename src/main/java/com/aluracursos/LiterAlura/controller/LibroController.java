package com.aluracursos.LiterAlura.controller;

import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public Libro guardarLibro(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }

    @GetMapping
    public List<Libro> listaLibros(){
        return libroService.listarLibros();
    }

    @GetMapping("/idioma/{idioma}")
    public List<Libro> listarLibrosPorIdioma(@PathVariable String idioma){
        return libroService.listarLibrosPorIdioma(idioma);
    }
}

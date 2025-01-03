package com.aluracursos.LiterAlura.controller;

import com.aluracursos.LiterAlura.model.Autor;
import com.aluracursos.LiterAlura.service.AutorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    @GetMapping("/autores")
    public List<Autor> listarAutores() {
        return autorService.listarAutores();
    }

    @GetMapping("/autores/vivos")
    public List<Autor> listarAutoresVivosEnAnio(@RequestParam int anio) {
        return autorService.listarAutoresVivosEnAnio(anio);
    }
}
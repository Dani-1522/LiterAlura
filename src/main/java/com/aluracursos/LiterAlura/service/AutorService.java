package com.aluracursos.LiterAlura.service;


import com.aluracursos.LiterAlura.model.Autor;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        return autorRepository.findAll().stream()
                .filter(autor -> autor.getAnioNacimiento() != null && autor.getAnioNacimiento() <= anio)
                .filter(autor -> autor.getAnioFallecimiento() == null || autor.getAnioFallecimiento() > anio)
                .collect(Collectors.toList());
    }
}

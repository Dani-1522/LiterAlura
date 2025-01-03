package com.aluracursos.LiterAlura.service;

import com.aluracursos.LiterAlura.model.Autor;
import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final RestTemplate restTemplate;

    private static final String API_URL ="https://gutendex.com/books";

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository,RestTemplate restTemplate ){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.restTemplate = restTemplate;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        String url = API_URL + "?search=" + titulo;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null || !response.containsKey("results")) {
            throw new RuntimeException("No se encontraron resultados para el título: " + titulo);
        }

        List<Map<String, Object>> resultados = (List<Map<String, Object>>) response.get("results");
        if (resultados.isEmpty()) {
            throw new RuntimeException("No se encontraron resultados para el título: " + titulo);
        }
        Map<String, Object> libroData = resultados.get(0);

        // Crear autor
        List<Map<String, Object>> autoresData = (List<Map<String, Object>>) libroData.get("authors");
        Map<String, Object> autorData = autoresData.get(0);
        Autor autor = new Autor();
        autor.setNombre((String) autorData.get("name"));
        autor.setAnioNacimiento((Integer) autorData.get("birth_year"));
        autor.setAnioFallecimiento((Integer) autorData.get("death_year"));
        autor = autorRepository.save(autor);

        // Crear libro
        Libro libro = new Libro();
        libro.setTitulo((String) libroData.get("title"));
        libro.setIdioma(((List<String>) libroData.get("languages")).get(0));
        libro.setDescargas((Integer) libroData.get("download_count"));
        libro.setAutor(autor);

        return libroRepository.save(libro);
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
    public long contarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma).size();
    }
}

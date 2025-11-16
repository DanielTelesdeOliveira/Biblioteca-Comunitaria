package onlineBiblioteca.biblioteca.dto.Livro;

import onlineBiblioteca.biblioteca.dto.Genero.GeneroResponseDTO;
import onlineBiblioteca.biblioteca.model.Genero;

public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private GeneroResponseDTO genero;

    public LivroResponseDTO(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public GeneroResponseDTO getGenero() {
        return genero;
    }
    public void setGenero(GeneroResponseDTO genero) {
        this.genero = genero;
    }

    
}

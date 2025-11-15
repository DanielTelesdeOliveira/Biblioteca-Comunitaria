package onlineBiblioteca.biblioteca.dto.Livro;

public class LivroRequestDTO {
    private String titulo;
    private String autor;
    private String categoria;
    private Long generoId; 

    public LivroRequestDTO(){}

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
    public Long getGeneroId() {
        return generoId;
    }
    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }
    

}

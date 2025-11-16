package onlineBiblioteca.biblioteca.service;

import onlineBiblioteca.biblioteca.dto.Genero.GeneroResponseDTO;
import onlineBiblioteca.biblioteca.dto.Livro.LivroResponseDTO;
import onlineBiblioteca.biblioteca.exception.Livro.LivroNaoEncontradoException;
import onlineBiblioteca.biblioteca.model.Genero;
import onlineBiblioteca.biblioteca.model.Livro;
import onlineBiblioteca.biblioteca.repository.LivroRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepo){
        this.livroRepository = livroRepo; 
    }

    public Livro criarLivro(String titulo, String autor, String categoria, Genero genero){
        Livro novoLivro = new Livro();

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setCategoria(categoria);
        novoLivro.setGenero(genero);

        return livroRepository.save(novoLivro);
    }

    public Livro atualizarLivro(Long livroId, String autor, String categoria, String titulo, Genero genero){
        Livro livro = livroRepository.findById(livroId)
            .orElseThrow(() -> new LivroNaoEncontradoException(livroId));

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livro.setGenero(genero);

        return livroRepository.save(livro);
    }

    public boolean encontrarLivro(long livroId){
        boolean check = livroRepository.existsById(livroId);
        return check;
    }

    public Livro getLivroId(long livroId){
        Livro livro = livroRepository.findById(livroId)
            .orElseThrow(()->new LivroNaoEncontradoException(livroId));
        return livro;
    }   

    public ArrayList<Livro> buscarTodosLivro(){
        ArrayList<Livro> lista = new ArrayList<>();

        for(Livro livro : livroRepository.findAll())
            lista.add(livro);

        return lista;    

    }

    public void deletarLivro(long id){
        Livro livro = livroRepository.findById(id)
            .orElseThrow(()->new LivroNaoEncontradoException(id));

        livroRepository.delete(livro);    
    }

    public LivroResponseDTO toDTO(Livro livro){
          LivroResponseDTO dto = new LivroResponseDTO();
          dto.setId(livro.getId());
          dto.setTitulo(livro.getTitulo());
          dto.setAutor(livro.getAutor());
          dto.setCategoria(livro.getCategoria());
          dto.setGenero(generoToDTO(livro.getGenero()));
          return dto;
    }

    public GeneroResponseDTO generoToDTO(Genero genero) {
        if (genero == null) return null;
    
        GeneroResponseDTO dto = new GeneroResponseDTO();

        dto.setId(genero.getId());
        dto.setNome(genero.getNome());
        
        return dto;
    }

}

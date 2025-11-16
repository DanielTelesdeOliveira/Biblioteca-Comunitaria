package onlineBiblioteca.biblioteca.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineBiblioteca.biblioteca.dto.Genero.GeneroResponseDTO;
import onlineBiblioteca.biblioteca.exception.Genero.GeneroNaoEncontradoException;
import onlineBiblioteca.biblioteca.model.Genero;
import onlineBiblioteca.biblioteca.repository.GeneroRepository;


@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepo){
        this.generoRepository = generoRepo; 
    }

    public Genero criarGenero(String nome){
        Genero novoGenero = new Genero();

        novoGenero.setNome(nome);

        return generoRepository.save(novoGenero);
    }

    public Genero atualizarGenero(Long generoId, String nome){
        Genero genero = generoRepository.findById(generoId)
            .orElseThrow(() -> new GeneroNaoEncontradoException(generoId));

        genero.setNome(nome);

        return generoRepository.save(genero);
    }

    public boolean encontrarGenero(long generoId){
        boolean check = generoRepository.existsById(generoId);
        return check;
    }

    public Genero getGeneroId(long generoId){
        Genero genero = generoRepository.findById(generoId)
            .orElseThrow(()->new GeneroNaoEncontradoException(generoId));
        return genero;
    }   

    public ArrayList<Genero> buscarTodosGeneros(){
        ArrayList<Genero> lista = new ArrayList<>();

        for(Genero genero : generoRepository.findAll())
            lista.add(genero);

        return lista;    

    }

    public void deletarGenero(long id){
        Genero genero = generoRepository.findById(id)
            .orElseThrow(()->new GeneroNaoEncontradoException(id));

        generoRepository.delete(genero);    
    }

    public GeneroResponseDTO toDTO(Genero genero){
          GeneroResponseDTO dto = new GeneroResponseDTO();
          dto.setId(genero.getId());
          dto.setNome(genero.getNome());
          return dto;
    }



}

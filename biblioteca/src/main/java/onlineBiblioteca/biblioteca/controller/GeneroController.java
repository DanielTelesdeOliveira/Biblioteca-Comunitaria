package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.model.Genero;
import onlineBiblioteca.biblioteca.repository.GeneroRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;


    @PostMapping("/cadastro")
    public Genero cadastrarGenero(@RequestBody Genero genero) {
        return generoRepository.save(genero);
    }

    @GetMapping("/lista")
    public List<Genero> listarGenero(){
        return generoRepository.findAll();
    }
    
    @GetMapping("/busca")
    public List<Genero> buscarGenero(@RequestParam String nome) {
        return generoRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarGenero(@PathVariable Long id) {
        return generoRepository.findById(id)
                .map(genero -> {
                    generoRepository.delete(genero);
                    return ResponseEntity.ok("Genero deletado com sucesso!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

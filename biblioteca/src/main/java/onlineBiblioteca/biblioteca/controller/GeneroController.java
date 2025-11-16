package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.dto.Genero.GeneroResponseDTO;
import onlineBiblioteca.biblioteca.model.Genero;
import onlineBiblioteca.biblioteca.service.GeneroService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;


    @PostMapping("/cadastro")
   public ResponseEntity<GeneroResponseDTO> cadastrarGenero(@RequestBody Genero genero) {
       Genero novo = generoService.criarGenero(
                genero.getNome()
        );
        
        return ResponseEntity.ok(generoService.toDTO(novo));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<GeneroResponseDTO>> listarGenero(){
        List<GeneroResponseDTO> listaDTO = generoService.buscarTodosGeneros()
                .stream()
                .map(generoService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<GeneroResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<GeneroResponseDTO> listaDTO = generoService.buscarTodosGeneros()
                .stream()
                .filter(g -> g.getNome().toLowerCase().contains(nome.toLowerCase()))
                .map(generoService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GeneroResponseDTO> atualizarGenero(@PathVariable Long id,@RequestBody Genero genero) {
        Genero atualizado = generoService.atualizarGenero(
                id,
                genero.getNome()
        );
        return ResponseEntity.ok(generoService.toDTO(atualizado));
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarGenero(@PathVariable Long id) {
            generoService.deletarGenero(id);
            return ResponseEntity.ok("Genero deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> buscarPorId(@PathVariable Long id) {
        Genero genero = generoService.getGeneroId(id); // lança exceção se não encontrar
        return ResponseEntity.ok(generoService.toDTO(genero));
    }
}
 
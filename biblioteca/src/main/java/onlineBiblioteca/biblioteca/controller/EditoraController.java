package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.model.Editora;
import onlineBiblioteca.biblioteca.repository.EditoraRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;


    @PostMapping("/cadastro")
    public Editora cadastrarEditora(@RequestBody Editora editora) {
        return editoraRepository.save(editora);
    }

    @GetMapping("/lista")
    public List<Editora> listarEditora(){
        return editoraRepository.findAll();
    }
    
    @GetMapping("/busca")
    public List<Editora> buscarEditora(@RequestParam String nome) {
        return editoraRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarEditora(@PathVariable Long id) {
        return editoraRepository.findById(id)
                .map(editora -> {
                    editoraRepository.delete(editora);
                    return ResponseEntity.ok("Editora deletado com sucesso!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

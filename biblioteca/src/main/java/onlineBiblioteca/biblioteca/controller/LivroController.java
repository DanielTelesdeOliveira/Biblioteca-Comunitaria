package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.model.Livro;
import onlineBiblioteca.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping("/cadastro")
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping("/lista")
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    @GetMapping("/buscar")
    public List<Livro> buscarPorTitulo(@RequestParam String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Deletar um livro pelo ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return ResponseEntity.ok("Livro deletado com sucesso!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


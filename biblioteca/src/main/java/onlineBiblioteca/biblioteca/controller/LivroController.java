package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.model.Livro;
import onlineBiblioteca.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    // Cadastrar novo livro
    @PostMapping("/cadastro")
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    // Listar todos os livros
    @GetMapping("/lista")
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    // Buscar por título (recebe título no body)
    @PostMapping("/buscar")
    public ResponseEntity<List<Livro>> buscarPorTitulo(@RequestBody Map<String, String> body) {
        String titulo = body.get("titulo");
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);

        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livros);
    }


    // Deletar livro (passando o título no body)
    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarLivro(@RequestBody Map<String, String> body) {
        String titulo = body.get("titulo");
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);

        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        livros.forEach(livroRepository::delete);
        return ResponseEntity.ok("Livro deletado com sucesso!");
    }
}

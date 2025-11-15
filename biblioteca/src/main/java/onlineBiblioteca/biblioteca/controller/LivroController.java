package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.dto.Livro.LivroResponseDTO;
import onlineBiblioteca.biblioteca.model.Livro;
import onlineBiblioteca.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/cadastro")
    public ResponseEntity<LivroResponseDTO> cadastrarLivro(@RequestBody Livro livro) {
       Livro criado = livroService.criarLivro(
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCategoria(),
                livro.getGenero()
        );
        
        return ResponseEntity.ok(livroService.toDTO(criado));
    }


    @GetMapping("/lista")
    public ResponseEntity<List<LivroResponseDTO>> listarLivros() {
        List<LivroResponseDTO> listaDTO = livroService.buscarTodosLivro()
                .stream()
                .map(livroService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorTitulo(@RequestParam String titulo) {
        List<LivroResponseDTO> listaDTO = livroService.buscarTodosLivro()
                .stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .map(livroService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(
            @PathVariable Long id,
            @RequestBody Livro livroAtualizado
    ) {
        Livro atualizado = livroService.atualizarLivro(
                id,
                livroAtualizado.getAutor(),
                livroAtualizado.getCategoria(),
                livroAtualizado.getTitulo(),
                livroAtualizado.getGenero()
        );
        return ResponseEntity.ok(livroService.toDTO(atualizado));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id); // se não existir, lança exceção tratada pelo GlobalExceptionHandler
        return ResponseEntity.ok("Livro deletado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.getLivroId(id); // lança exceção se não encontrar
        return ResponseEntity.ok(livroService.toDTO(livro));
    }
}

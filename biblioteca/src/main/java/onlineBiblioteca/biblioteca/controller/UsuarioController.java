package onlineBiblioteca.biblioteca.controller;

import onlineBiblioteca.biblioteca.model.Usuario;
import onlineBiblioteca.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ✅ Listar todos os usuários
    @GetMapping("/lista")
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    // ✅ Buscar usuário pelo CPF (passado no body)
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarPorCpf(@RequestBody Usuario usuario) {
        return usuarioService.buscarPorCpf(usuario.getCpf())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Cadastrar novo usuário
    @PostMapping("/cadastro")
    public ResponseEntity<?> criar(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.salvar(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Atualizar dados do usuário pelo CPF
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
        try {
            Usuario atualizado = usuarioService.atualizar(usuario.getCpf(), usuario);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            // CPF inválido ou já existente
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Usuário não encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletar(@RequestBody Map<String, String> body) {
        String cpf = body.get("cpf");
        String mensagem = usuarioService.deletarPorCpf(cpf);
        return ResponseEntity.ok(mensagem);
    }
}

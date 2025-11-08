package onlineBiblioteca.biblioteca.service;

import onlineBiblioteca.biblioteca.model.Usuario;
import onlineBiblioteca.biblioteca.repository.UsuarioRepository;
import onlineBiblioteca.biblioteca.utils.CpfCnpjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por CPF
    public Optional<Usuario> buscarPorCpf(String cpf) {
        String cpfFormatado = formatarCpf(cpf);
        return usuarioRepository.findByCpf(cpfFormatado);
    }

    // Salvar novo usuário
    public Usuario salvar(Usuario usuario) {
        String cpf = usuario.getCpf();

        // Validação do CPF
        if (!CpfCnpjUtils.isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        // Formatar o CPF sempre com máscara antes de salvar
        String cpfFormatado = formatarCpf(cpf);
        usuario.setCpf(cpfFormatado);

        return usuarioRepository.save(usuario);
    }

    // Atualizar dados do usuário existente pelo CPF
    public Usuario atualizar(String cpf, Usuario novosDados) {
        String cpfFormatado = formatarCpf(cpf);

        Usuario usuarioExistente = usuarioRepository.findByCpf(cpfFormatado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o CPF informado."));

        usuarioExistente.setNome(novosDados.getNome());
        usuarioExistente.setEndereco(novosDados.getEndereco());
        usuarioExistente.setEmail(novosDados.getEmail());
        usuarioExistente.setTelefone(novosDados.getTelefone());

        return usuarioRepository.save(usuarioExistente);
    }

    // Deletar usuário pelo CPF com mensagem
    public String deletarPorCpf(String cpf) {
        String cpfFormatado = formatarCpf(cpf);

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCpf(cpfFormatado);
        if (usuarioOpt.isPresent()) {
            usuarioRepository.delete(usuarioOpt.get());
            return "Usuário com CPF " + cpfFormatado + " foi deletado com sucesso!";
        } else {
            return "Usuário com CPF " + cpfFormatado + " não encontrado.";
        }
    }

    // ---------- MÉTODO AUXILIAR ----------
    private String formatarCpf(String cpf) {
        // Remove tudo que não é número
        String numeros = cpf.replaceAll("\\D", "");

        // Valida o CPF novamente
        if (!CpfCnpjUtils.isCpfValido(numeros)) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        // Formata sempre com máscara XXX.XXX.XXX-XX
        return numeros.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4");
    }
}

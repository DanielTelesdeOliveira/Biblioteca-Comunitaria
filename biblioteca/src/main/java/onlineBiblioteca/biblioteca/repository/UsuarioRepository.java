package onlineBiblioteca.biblioteca.repository;

import onlineBiblioteca.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuário pelo CPF
    Optional<Usuario> findByCpf(String cpf);

    // Deletar usuário pelo CPF
    void deleteByCpf(String cpf);

    // Verificar se já existe um usuário com o CPF
    boolean existsByCpf(String cpf);
}

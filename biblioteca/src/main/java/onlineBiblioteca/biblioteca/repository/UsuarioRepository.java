package onlineBiblioteca.biblioteca.repository;
import onlineBiblioteca.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

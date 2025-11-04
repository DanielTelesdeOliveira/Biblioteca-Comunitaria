package onlineBiblioteca.biblioteca.repository;
import onlineBiblioteca.biblioteca.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}

package onlineBiblioteca.biblioteca.repository;
import onlineBiblioteca.biblioteca.model.Genero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    List<Genero> findByNomeContainingIgnoreCase(String nome);
}

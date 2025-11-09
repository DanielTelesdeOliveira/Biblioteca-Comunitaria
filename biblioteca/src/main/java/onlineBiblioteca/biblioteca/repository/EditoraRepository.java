package onlineBiblioteca.biblioteca.repository;
import onlineBiblioteca.biblioteca.model.Editora;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{
    List<Editora> findByNomeContainingIgnoreCase(String nome);
}

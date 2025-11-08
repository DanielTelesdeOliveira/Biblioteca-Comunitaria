package onlineBiblioteca.biblioteca.repository;
import onlineBiblioteca.biblioteca.model.Emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
}

package onlineBiblioteca.biblioteca.exception.Emprestimo;
import java.time.LocalDate;

public class DataEmprestimoInvalidaException extends RuntimeException{
    public DataEmprestimoInvalidaException(LocalDate data){
        super("A data: " +data+ " é invalida!");
    }
}

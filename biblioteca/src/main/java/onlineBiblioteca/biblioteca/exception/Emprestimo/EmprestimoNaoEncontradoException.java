package onlineBiblioteca.biblioteca.exception.Emprestimo;

public class EmprestimoNaoEncontradoException extends RuntimeException{
    
    public EmprestimoNaoEncontradoException(Long Id){
        super("Emprestimo de Id: " + Id + " Nao Encontrado!");
    }
}

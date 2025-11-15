package onlineBiblioteca.biblioteca.exception.Livro;

public class LivroNaoEncontradoException extends RuntimeException{

    public LivroNaoEncontradoException(Long id){
        super("Livro de id: " +id+ " Nao encontrado!");
    }
}
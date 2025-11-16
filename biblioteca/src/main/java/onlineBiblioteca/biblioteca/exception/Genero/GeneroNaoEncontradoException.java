package onlineBiblioteca.biblioteca.exception.Genero;

public class GeneroNaoEncontradoException extends RuntimeException{
    public GeneroNaoEncontradoException(Long id){
        super("Genero de id: " +id+ " nao encontrado!");
    }

}
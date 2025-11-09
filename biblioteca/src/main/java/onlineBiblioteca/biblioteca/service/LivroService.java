package onlineBiblioteca.biblioteca.service;

import onlineBiblioteca.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;


}

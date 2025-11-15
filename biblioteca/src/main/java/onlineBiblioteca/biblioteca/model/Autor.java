package onlineBiblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name="autor")
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Nome_Autor", nullable=false)
    private String nome;


    public Autor(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor(){
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

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
    private int id;

    @Column(name="Nome_Autor", nullable=false)
    private String nome;


    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor(){
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
    this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

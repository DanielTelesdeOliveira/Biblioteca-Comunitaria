package onlineBiblioteca.biblioteca.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Table(name="Tipo_User")
@Entity
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tipo")
    private int id;

    @Column(name="Nome_Tipo", nullable=false)
    private String nome;

    @OneToMany(mappedBy="tipo")
    private ArrayList<Usuario> usuarios = new ArrayList<>();


    public TipoUsuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoUsuario(){

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setId(int id) {
    this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}

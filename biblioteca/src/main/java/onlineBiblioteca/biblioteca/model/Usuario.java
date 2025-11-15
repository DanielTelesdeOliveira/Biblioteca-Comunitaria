package onlineBiblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Email_User", nullable=false)
    private String email;

        
    @Column(name="Nome_User", nullable=false)
    private String nome;   
    
    @Column(name="CPF_User", nullable=false)
    private String cpf;

    @ManyToOne
    @JoinColumn(
        name = "fk_tipo_user_id_tipo"
    )
    private TipoUsuario tipo;


    public Usuario(Long id, String email, String nome, String cpf) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Usuario(){
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }    


    

}

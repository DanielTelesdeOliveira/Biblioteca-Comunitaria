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
    private int id;

    @Column(name="Email_User", nullable=false)
    private String email;

        
    @Column(name="Nome_User", nullable=false)
    private String nome;   
    
    @Column(name="CPF_User", nullable=false)
    private String cpf;

    @ManyToOne
    @JoinColumn(
        name = "fk_Tipo_User_ID_Tipo", //Nome da coluna da tabela usuario
        referencedColumnName = "ID_Tipo" // coluna referenciada na tabela Tipo_User
    )
    private TipoUsuario tipo;


    public Usuario(int id, String email, String nome, String cpf) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Usuario(){
    }

    public int getId() {
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

    public void setId(int id) {
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

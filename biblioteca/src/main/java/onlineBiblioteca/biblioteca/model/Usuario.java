package onlineBiblioteca.biblioteca.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long id;

    @Column(nullable = false, name = "nome_user")
    private String nome;

    @Column(nullable = false, name = "endereco_user")
    private String endereco;

    @Column(nullable = false, name = "email_user")
    private String email;

    @Column(nullable = false, name = "telefone_user")
    private String telefone;

    @Column(nullable = false, unique = true, name = "cpf_user")
    private String cpf;

    // Relação com TipoUsuario
    @ManyToOne
    @JoinColumn(name = "ID_Tipo")
    private TipoUsuario tipo;

    public Usuario() {}

    public Usuario(String nome, String endereco, String email, String telefone, String cpf, TipoUsuario tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

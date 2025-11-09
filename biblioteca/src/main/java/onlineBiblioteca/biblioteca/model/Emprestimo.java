package onlineBiblioteca.biblioteca.model;
import java.time.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name="emprestimo")
@Entity
public class Emprestimo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Data_Empre", nullable=true)
    private LocalDate data_emprestimo;

    @Column(name="Data_Devolucao", nullable=true)
    private LocalDate data_devolucao;
    
    @Column(name="Data_Previ_Devol", nullable=true)
    private LocalDate previsao_devolucao;

    @Column(name="Multa_Emprestimo", nullable=true)
    private double multa;

    //boolean finalizado ou devolvido (?)
    //Long id usuario -- chave estrangeira para ver quem esta com o livro

    public Emprestimo(){}

    public Emprestimo(int id, LocalDate data_emprestimo, LocalDate data_devolucao, LocalDate previsao_devolucao, double multa) {
        this.id = id;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
        this.previsao_devolucao = previsao_devolucao;
        this.multa = multa;
    }

    public Emprestimo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public LocalDate getData_emprestimo() {
    return data_emprestimo;
    }

    public LocalDate getPrevisao_devolucao() {
        return previsao_devolucao;
    }

    public double getMulta() {
    return multa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public void setPrevisao_devolucao(LocalDate previsao_devolucao) {
        this.previsao_devolucao = previsao_devolucao;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

}
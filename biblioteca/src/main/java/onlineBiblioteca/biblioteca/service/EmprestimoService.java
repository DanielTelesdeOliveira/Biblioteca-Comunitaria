package onlineBiblioteca.biblioteca.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.hibernate.mapping.List;

import onlineBiblioteca.biblioteca.exception.Emprestimo.dataEmprestimoInvalidaException;
import onlineBiblioteca.biblioteca.exception.Emprestimo.emprestimoNaoEncontradoException;
import onlineBiblioteca.biblioteca.model.Emprestimo;
import onlineBiblioteca.biblioteca.repository.EmprestimoRepository;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository empreRepo){
        this.emprestimoRepository = empreRepo; 
    }

    public void criarEmprestimo(LocalDate data_emprestimo, LocalDate data_devolucao, LocalDate previsao_devolucao, double multa){
        Emprestimo novoEmprestimo = new Emprestimo();

        if(data_emprestimo.isAfter(LocalDate.now())){
            throw new dataEmprestimoInvalidaException(data_emprestimo);
        }

        if(data_devolucao.isBefore(data_emprestimo)){
            throw new dataEmprestimoInvalidaException(data_devolucao);
        }


        novoEmprestimo.setData_emprestimo(data_emprestimo);
        novoEmprestimo.setData_devolucao(data_devolucao);
        novoEmprestimo.setPrevisao_devolucao(previsao_devolucao);
        novoEmprestimo.setMulta(multa);

        emprestimoRepository.save(novoEmprestimo);
    }

    public Emprestimo atualizarEmprestimo(Long emprestimoId, LocalDate data_emprestimo, LocalDate data_devolucao, LocalDate previsao_devolucao, double multa){
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new emprestimoNaoEncontradoException(emprestimoId));

        
        if(data_emprestimo.isAfter(LocalDate.now())){
            throw new dataEmprestimoInvalidaException(data_emprestimo);
        }

        if(data_devolucao.isBefore(data_emprestimo)){
            throw new dataEmprestimoInvalidaException(data_devolucao);
        }
    

        empre.setData_emprestimo(data_emprestimo); 
        empre.setData_devolucao(data_devolucao);
        empre.setPrevisao_devolucao(previsao_devolucao);
        empre.setMulta(multa);

        return emprestimoRepository.save(empre);
    }

    public boolean encontrarEmprestimo(long emprestimoId){
        boolean check = emprestimoRepository.existsById(emprestimoId);
        return check;
    }

    public Emprestimo getEmprestimoId(long emprestimoId){
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(()->new emprestimoNaoEncontradoException(emprestimoId));
        return empre;
    }   

    public ArrayList<Emprestimo> buscarTodosEmprestimos(){
        ArrayList<Emprestimo> lista = new ArrayList<>();

        for(Emprestimo empre : emprestimoRepository.findAll())
            lista.add(empre);

        return lista;    

    }

    public void deletarEmprestimo(Long emprestimoId){
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(()->new emprestimoNaoEncontradoException(emprestimoId));

            emprestimoRepository.delete(empre);

    }
    
    public boolean verificarDataDevo(Long emprestimoId){
        boolean expirar = false;
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new emprestimoNaoEncontradoException(emprestimoId));
        
       LocalDate data_prevista = empre.getPrevisao_devolucao();
       LocalDate data_devolucao = empre.getData_devolucao();
       LocalDate data_atual;

       //Nao foi devolvido
       if(data_devolucao == null){
            data_atual = LocalDate.now();
            if (data_atual.isAfter(data_prevista))
                expirar = true;
        } else{
            if(data_devolucao.isAfter(data_prevista))
                expirar = true;    
       }

       return expirar;
    }

    public double calcularMulta(Long emprestimoId){
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new emprestimoNaoEncontradoException(emprestimoId));

         LocalDate dataDevolutiva = empre.getData_devolucao();
         LocalDate dataPrevisao_Devolucao = empre.getPrevisao_devolucao();
         Long diasAtrasados;   
         double multa;
         
         if(dataDevolutiva == null){
            diasAtrasados = ChronoUnit.DAYS.between(dataPrevisao_Devolucao, LocalDate.now());
         } else{
            diasAtrasados = ChronoUnit.DAYS.between(dataPrevisao_Devolucao, dataDevolutiva);
         }

         multa = diasAtrasados*0.5;

         return multa;
    }

    public Emprestimo atualizarRetornoEmprestimo(Long emprestimoId, LocalDate dataDevolutiva){
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new emprestimoNaoEncontradoException(emprestimoId));

        if(dataDevolutiva.isBefore(empre.getData_emprestimo())){
            throw new dataEmprestimoInvalidaException(dataDevolutiva);
        }
    
            empre.setData_devolucao(dataDevolutiva);

            return emprestimoRepository.save(empre);
    }

    public Emprestimo delegarMulta(Long emprestimoId){
        Emprestimo empre = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new emprestimoNaoEncontradoException(emprestimoId));

        boolean devolucao = verificarDataDevo(emprestimoId);
        double multa = 0.0;
        if(devolucao){
            multa = calcularMulta(emprestimoId);
        }

        empre.setMulta(multa);
        return emprestimoRepository.save(empre);
    }



}

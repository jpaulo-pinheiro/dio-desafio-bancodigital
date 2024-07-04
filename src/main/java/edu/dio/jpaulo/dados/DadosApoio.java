package edu.dio.jpaulo.dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.dio.jpaulo.model.Banco;
import edu.dio.jpaulo.model.Cliente;
import edu.dio.jpaulo.model.Conta;
import edu.dio.jpaulo.model.ContaCorrente;
import edu.dio.jpaulo.model.ContaPoupanca;
import edu.dio.jpaulo.model.Operacao;
import edu.dio.jpaulo.model.TipoMovimentacao;
import edu.dio.jpaulo.model.TipoOperacao;
import lombok.Getter;

public class DadosApoio {

    private static DadosApoio uniqueInstance;

    private DadosApoio() {
        carregarDados();
    }

    public static synchronized DadosApoio getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new DadosApoio();

        return uniqueInstance;
    }

    @Getter
    private Banco banco;

    private void carregarDados() {
        this.banco = new Banco();
        banco.setNome("");
        banco.setContas(carregarDadosContas());
    }

    private List<Conta> carregarDadosContas() {
        List<Conta> contas = new ArrayList<>();

        Cliente cliente1 = new Cliente("Fulano");

        Conta conta1 = new ContaCorrente(cliente1);
        Conta conta2 = new ContaPoupanca(cliente1);

        // Dados das operações deixam saldo final = 0 pois não deve existir o método
        // setSaldo na conta pois o saldo é controlado pelos métodos que executam
        // operações e deve ser igual a zero para que os testes funcionem corretamente
        // ao realizar novas operações

        List<Operacao> operacoesConta1 = new ArrayList<>();
        operacoesConta1
                .add(new Operacao(TipoOperacao.DEPOSITO, TipoMovimentacao.CREDITO, 1000, LocalDate.of(2024, 1, 1), 0));
        operacoesConta1
                .add(new Operacao(TipoOperacao.SAQUE, TipoMovimentacao.DEBITO, 100, LocalDate.of(2024, 2, 1), 1000));
        operacoesConta1.add(new Operacao(TipoOperacao.TRANSFERENCIA, TipoMovimentacao.CREDITO, 500,
                LocalDate.of(2024, 3, 1), 900, conta2, null));
        operacoesConta1.add(new Operacao(TipoOperacao.SAQUE, TipoMovimentacao.DEBITO, 1400,
                LocalDate.of(2024, 4, 1), 400));
        conta1.getOperacoes().addAll(operacoesConta1);
        contas.add(conta1);

        List<Operacao> operacoesConta2 = new ArrayList<>();
        operacoesConta2.add(
                new Operacao(TipoOperacao.TRANSFERENCIA, TipoMovimentacao.CREDITO, 1000, LocalDate.of(2024, 4, 1), 0,
                        conta1, null));
        operacoesConta2
                .add(new Operacao(TipoOperacao.SAQUE, TipoMovimentacao.DEBITO, 1000, LocalDate.of(2024, 4, 1), 1000));
        conta2.getOperacoes().addAll(operacoesConta2);
        contas.add(conta2);

        return contas;
    }

}

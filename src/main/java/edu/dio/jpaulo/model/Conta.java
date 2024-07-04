package edu.dio.jpaulo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	@Getter // método getter existe apenas pela necessidade de criar dados iniciais ficticios
	private List<Operacao> operacoes;

	@Getter
	protected int agencia;

	@Getter
	protected int numero;

	@Getter
	protected double saldo;

	protected Cliente cliente;

	@Override
	public String toString() {
		return "[agencia=" + agencia + ", numero=" + numero + ", saldo=" + saldo + ", " + cliente + "]";
	}

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.operacoes = new ArrayList<>();
	}

	@Override
	public void sacar(double valor) {
		saldo -= valor;
		operacoes.add(new Operacao(TipoOperacao.SAQUE, TipoMovimentacao.DEBITO, valor, LocalDate.now(), saldo));
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
		operacoes.add(new Operacao(TipoOperacao.DEPOSITO, TipoMovimentacao.CREDITO, valor, LocalDate.now(), saldo));
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		Operacao saque = this.getOperacoes().stream().sorted((op1, op2) -> op2.getData().compareTo(op1.getData()))
				.findFirst().get();
		saque.setTipoOperacao(TipoOperacao.TRANSFERENCIA);
		saque.setDestino((Conta) contaDestino);

		contaDestino.depositar(valor);
		Operacao deposito = ((Conta) contaDestino).getOperacoes().stream()
				.sorted((op1, op2) -> op2.getData().compareTo(op1.getData())).findFirst().get();
		deposito.setTipoOperacao(TipoOperacao.TRANSFERENCIA);
		deposito.setOrigem(this);
	}

	protected void imprimirInfoComum() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		if(this instanceof ContaCorrente){
			System.out.println("Tipo: Conta Corrente");
		} else if (this instanceof ContaPoupanca){
			System.out.println("Tipo: Poupança");
		}
		System.out.println(String.format("Saldo Atual: %.2f", this.saldo));
	}

	@Override
	public void imprimirExtrato(final LocalDate dataInicio, LocalDate dataFim) {

		List<Operacao> extrato = operacoes.stream()
				.filter(d -> d.getData().isAfter(dataInicio.minusDays(1)) && d.getData().isBefore(dataFim.plusDays(1)))
				.sorted((op1, op2) -> op1.getData().compareTo(op2.getData())).collect(Collectors.toList());
		double saldo = extrato.get(0).getSaldoAnterior();

		System.out.println("===== Extrato de Conta =====");
		System.out.println();
		imprimirInfoComum();
		System.out.println();
		System.out.println("============================");
		System.out.println();
		System.out.println("Data do início: " + dataInicio);
		System.out.println("Data do fim: " + dataFim);
		System.out.println("Saldo inicial: " + saldo);
		System.out.println();
		System.out.println("Data\t\tOperação\t\tMovimentação\t\tValor\t\tSaldo");
		System.out.println();

		for (Operacao operacao : extrato) {

			if (operacao.getTipoMovimentacao().getMovimentacao().equals("-")) {
				saldo -= operacao.getValor();
			} else if (operacao.getTipoMovimentacao().getMovimentacao().equals("+")) {
				saldo += operacao.getValor();
			}

			System.out.println(
					operacao.getData() + "\t" + StringUtils.rightPad(operacao.getTipoOperacao().getDescricao(), 13)
							+ "\t\t" + StringUtils.rightPad(operacao.getTipoMovimentacao().getDescricao(), 12) + "\t\t"
							+ operacao.getValor() + "\t\t" + saldo);
		}

		System.out.println("");
		System.out.println("===== Fim do Extrato =======");

	}
}
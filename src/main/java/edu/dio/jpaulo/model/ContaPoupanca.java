package edu.dio.jpaulo.model;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirInfo() {
		System.out.println("=== Informações da Conta Poupança ===");
		super.imprimirInfoComum();
	}

	@Override
	public String toString() {
		return "Conta Poupança " + super.toString();
	}
}
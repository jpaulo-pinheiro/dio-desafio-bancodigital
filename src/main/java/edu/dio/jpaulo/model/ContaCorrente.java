package edu.dio.jpaulo.model;


public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirInfo() {
		System.out.println("=== Informações da Conta Corrente ===");
		super.imprimirInfoComum();
	}

	@Override
	public String toString() {
		return "Conta Corrente " + super.toString();
	}
	
}
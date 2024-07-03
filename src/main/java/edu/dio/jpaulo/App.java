package edu.dio.jpaulo;

import edu.dio.jpaulo.model.Cliente;
import edu.dio.jpaulo.model.Conta;
import edu.dio.jpaulo.model.ContaCorrente;
import edu.dio.jpaulo.model.ContaPoupanca;

/**
 * Hello world!
 */
public final class App {
    
    private App() {
    }

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		
		Conta cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
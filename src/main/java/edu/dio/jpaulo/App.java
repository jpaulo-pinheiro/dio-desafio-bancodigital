package edu.dio.jpaulo;

import java.time.LocalDate;

import edu.dio.jpaulo.dados.DadosApoio;
import edu.dio.jpaulo.model.Banco;
import edu.dio.jpaulo.model.Conta;

public final class App {
    
    private App() {
    }

	public static void main(String[] args) {

		Banco banco = DadosApoio.getInstance().getBanco();
		
		Conta conta1 = banco.getContas().get(0);
		Conta conta2 = banco.getContas().get(1);

		//banco.getContas().stream().forEach(System.out::println);

		conta1.imprimirExtrato(LocalDate.of(2024,1,1), LocalDate.of(2024,3,3));
		System.out.println();
		conta2.imprimirExtrato(LocalDate.of(2024,1,1), LocalDate.now());

		System.out.println();

		conta1.depositar(100);
		conta2.depositar(300);
		conta2.transferir(200, conta1);

		conta1.imprimirExtrato(LocalDate.of(2024,3,1), LocalDate.now());
		System.out.println();
		conta2.imprimirExtrato(LocalDate.of(2024,4,1), LocalDate.now());

	}

}
package edu.dio.jpaulo.model;

import java.time.LocalDate;

public interface IConta {
	
	void sacar(double valor);
	
	void depositar(double valor);
	
	void transferir(double valor, IConta contaDestino);
	
	void imprimirExtrato(final LocalDate dataInicio, LocalDate dataFim);

	void imprimirInfo();
}
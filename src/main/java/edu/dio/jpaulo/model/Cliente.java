package edu.dio.jpaulo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Cliente {

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + "]";
	}

	@Getter
	@Setter
	private String nome;

}
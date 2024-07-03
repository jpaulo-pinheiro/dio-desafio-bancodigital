package edu.dio.jpaulo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Banco {

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private List<Conta> contas;

}

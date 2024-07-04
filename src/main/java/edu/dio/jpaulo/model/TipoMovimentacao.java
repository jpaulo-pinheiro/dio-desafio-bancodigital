package edu.dio.jpaulo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Definição do enum TipoMovimentacao
@AllArgsConstructor
public enum TipoMovimentacao {

    CREDITO("Crédito", "+"),
    DEBITO("Débito", "-");

    @Getter
    private final String descricao;

    @Getter
    private final String movimentacao;
}


package edu.dio.jpaulo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Definição do enum TipoOperacao
@AllArgsConstructor
public enum TipoOperacao {
    
    DEPOSITO("Depósito"),
    SAQUE("Saque"),
    TRANSFERENCIA("Transferência"),
    PAGAMENTO("Pagamento");

    @Getter
    String descricao;
}


package edu.dio.jpaulo.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Operacao {

    @Getter
    @Setter
    @NonNull
    private TipoOperacao tipoOperacao;

    @Getter
    @Setter
    @NonNull
    private TipoMovimentacao tipoMovimentacao;

    @Getter
    @Setter
    @NonNull
    private double valor;

    @Getter
    @Setter
    @NonNull
    private LocalDate data;

    @Getter
    @Setter
    @NonNull
    private double saldoAnterior;

    @Getter
    @Setter
    private Conta origem;

    @Getter
    @Setter
    private Conta destino;

    public Operacao(@NonNull TipoOperacao tipoOperacao, @NonNull TipoMovimentacao tipoMovimentacao,
            @NonNull double valor, @NonNull LocalDate data, @NonNull double saldoAnterior, Conta origem,
            Conta destino) {
        this.tipoOperacao = tipoOperacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valor = valor;
        this.data = data;
        this.saldoAnterior = saldoAnterior;
        this.origem = origem;
        this.destino = destino;
    }



   }
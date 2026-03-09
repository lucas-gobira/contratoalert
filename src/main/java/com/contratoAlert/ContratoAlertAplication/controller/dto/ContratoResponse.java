package com.contratoAlert.ContratoAlertAplication.controller.dto;

import com.contratoAlert.ContratoAlertAplication.domain.Contrato;

import java.time.LocalDate;

public record ContratoResponse(
        Long id,
        String titulo,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        boolean ativo
) {
    public static ContratoResponse from(Contrato c) {
        return new ContratoResponse(
                c.getId(),
                c.getTitulo(),
                c.getDescricao(),
                c.getDataInicio(),
                c.getDataFim(),
                c.isAtivo()
        );
    }
}

package com.contratoAlert.ContratoAlertAplication.controller.dto;

import java.time.LocalDate;

public record ContratoRequest(
        String titulo,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim
) {}


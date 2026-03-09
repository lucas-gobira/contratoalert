package com.contratoAlert.ContratoAlertAplication.controller.dto;

public record RegisterRequest(
        String empresaNome,
        String cnpj,
        String adminNome,
        String adminEmail,
        String adminSenha
) {}


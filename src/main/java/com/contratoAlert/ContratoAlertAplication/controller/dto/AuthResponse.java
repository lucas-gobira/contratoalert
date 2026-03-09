package com.contratoAlert.ContratoAlertAplication.controller.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {}


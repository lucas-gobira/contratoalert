package com.contratoAlert.ContratoAlertAplication.config;

import lombok.Data;

@Data
public class UsuarioAuth {

    private final Long userId;
    private final Long empresaId;
    private final String email;
    private final String role;

    public UsuarioAuth(
            Long userId,
            Long empresaId,
            String email,
            String role
    ) {
        this.userId = userId;
        this.empresaId = empresaId;
        this.email = email;
        this.role = role;
    }
}

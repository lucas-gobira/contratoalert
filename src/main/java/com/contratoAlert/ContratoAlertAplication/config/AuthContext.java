package com.contratoAlert.ContratoAlertAplication.config;

import lombok.Data;

@Data
public class AuthContext {

    private static final ThreadLocal<UsuarioAuth> CONTEXT = new ThreadLocal<>();

    public static void set(UsuarioAuth auth) {
        CONTEXT.set(auth);
    }
    public static UsuarioAuth get() {
        return CONTEXT.get();
    }

    public static Long empresaId() {
        return get().getEmpresaId();
    }

    public static Long userId() {
        return get().getUserId();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}


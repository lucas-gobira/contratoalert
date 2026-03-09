package com.contratoAlert.ContratoAlertAplication.service;

import com.contratoAlert.ContratoAlertAplication.domain.RefreshToken;
import com.contratoAlert.ContratoAlertAplication.domain.Usuario;
import com.contratoAlert.ContratoAlertAplication.exception.InvalidRefreshTokenException;
import com.contratoAlert.ContratoAlertAplication.repository.RefreshTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshTokenService(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    public RefreshToken create(Usuario usuario) {
        RefreshToken rt = new RefreshToken();
        rt.setToken(UUID.randomUUID().toString());
        rt.setUsuario(usuario);
        rt.setExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS));
        return repository.save(rt);
    }

    public RefreshToken validate(String token) {

        RefreshToken rt = repository.findByToken(token)
                .orElseThrow(() ->
                        new InvalidRefreshTokenException("Refresh token inválido")
                );

        if (rt.isRevoked() || rt.getExpiresAt().isBefore(Instant.now())) {
            throw new InvalidRefreshTokenException(
                    "Refresh token expirado ou revogado"
            );
        }

        return rt;
    }

    public void revoke(RefreshToken rt) {
        rt.setRevoked(true);
        repository.save(rt);
    }

    public void revokeByToken(String token) {
        RefreshToken rt = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token inválido"));

        rt.setRevoked(true);
        repository.save(rt);
    }

    @Scheduled(cron = "0 0 3 * * ?") // todo dia 3h
    public void cleanupRefreshTokens() {
        repository.deleteExpiredOrRevoked(Instant.now());
    }

}

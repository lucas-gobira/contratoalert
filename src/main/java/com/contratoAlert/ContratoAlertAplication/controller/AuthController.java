package com.contratoAlert.ContratoAlertAplication.controller;

import com.contratoAlert.ContratoAlertAplication.controller.dto.*;
import com.contratoAlert.ContratoAlertAplication.domain.Empresa;
import com.contratoAlert.ContratoAlertAplication.domain.RefreshToken;
import com.contratoAlert.ContratoAlertAplication.domain.Role;
import com.contratoAlert.ContratoAlertAplication.domain.Usuario;
import com.contratoAlert.ContratoAlertAplication.repository.EmpresaRepository;
import com.contratoAlert.ContratoAlertAplication.repository.UsuarioRepository;
import com.contratoAlert.ContratoAlertAplication.service.JwtService;
import com.contratoAlert.ContratoAlertAplication.service.RefreshTokenService;
import com.contratoAlert.ContratoAlertAplication.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UsuarioService usuarioService;


    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, JwtService jwtService, RefreshTokenService refreshTokenService, EmpresaRepository empresaRepository, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );

        Usuario usuario = usuarioRepository
                .findByEmail(request.email())
                .orElseThrow();

        String accessToken = jwtService.generateToken(usuario);
        String refreshToken = refreshTokenService.create(usuario).getToken();

        return new AuthResponse(accessToken, refreshToken);
    }


    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {

        RefreshToken rt = refreshTokenService.validate(request.refreshToken());

        Usuario usuario = rt.getUsuario();

        //ROTATION
        refreshTokenService.revoke(rt);
        RefreshToken novo = refreshTokenService.create(usuario);

        String accessToken = jwtService.generateToken(usuario);

        return new AuthResponse(accessToken, novo.getToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody LogoutRequest request
    ) {
        refreshTokenService.revokeByToken(request.refreshToken());
        return ResponseEntity.noContent().build(); // 204
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest req) {

        usuarioService.createInterpriseAndUserAdmin(req);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}


package com.contratoAlert.ContratoAlertAplication.service;

import com.contratoAlert.ContratoAlertAplication.controller.dto.RegisterRequest;
import com.contratoAlert.ContratoAlertAplication.domain.Empresa;
import com.contratoAlert.ContratoAlertAplication.domain.Role;
import com.contratoAlert.ContratoAlertAplication.domain.Usuario;
import com.contratoAlert.ContratoAlertAplication.repository.EmpresaRepository;
import com.contratoAlert.ContratoAlertAplication.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            EmpresaRepository empresaRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario createInterpriseAndUserAdmin(RegisterRequest req) {
        if (usuarioRepository.findByEmail(req.adminEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Empresa empresa = new Empresa();
        empresa.setNome(req.empresaNome());
        empresa.setCnpj(req.cnpj());
        empresaRepository.save(empresa);

        Usuario admin = new Usuario();
        admin.setNome(req.adminNome());
        admin.setEmail(req.adminEmail());
        admin.setSenha(passwordEncoder.encode(req.adminSenha()));
        admin.setRole(Role.ADMIN);
        admin.setEmpresa(empresa);

        return usuarioRepository.save(admin);
    }


}

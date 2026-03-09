package com.contratoAlert.ContratoAlertAplication.repository;

import com.contratoAlert.ContratoAlertAplication.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByIdAndEmpresaId(Long id, Long empresaId);

}

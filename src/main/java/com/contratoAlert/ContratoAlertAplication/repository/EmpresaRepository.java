package com.contratoAlert.ContratoAlertAplication.repository;

import com.contratoAlert.ContratoAlertAplication.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}

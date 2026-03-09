package com.contratoAlert.ContratoAlertAplication.repository;

import com.contratoAlert.ContratoAlertAplication.domain.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

        List<Contrato> findAllByEmpresaId(Long empresaId);

        Optional<Contrato> findByIdAndEmpresaId(Long id, Long empresaId);

}

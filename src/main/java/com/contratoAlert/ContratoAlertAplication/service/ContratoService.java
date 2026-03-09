package com.contratoAlert.ContratoAlertAplication.service;

import com.contratoAlert.ContratoAlertAplication.config.AuthContext;
import com.contratoAlert.ContratoAlertAplication.controller.dto.ContratoRequest;
import com.contratoAlert.ContratoAlertAplication.domain.Contrato;
import com.contratoAlert.ContratoAlertAplication.domain.Empresa;
import com.contratoAlert.ContratoAlertAplication.repository.ContratoRepository;
import com.contratoAlert.ContratoAlertAplication.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository repository;
    private final EmpresaRepository empresaRepository;

    public List<Contrato> listar() {
        return repository.findAllByEmpresaId(AuthContext.empresaId());
    }

    public Contrato buscar(Long id) {
        return repository.findByIdAndEmpresaId(id, AuthContext.empresaId())
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public Contrato criar(ContratoRequest req) {
        Empresa empresa = empresaRepository.getReferenceById(
                AuthContext.empresaId()
        );

        Contrato contrato = new Contrato();
        contrato.setTitulo(req.titulo());
        contrato.setDescricao(req.descricao());
        contrato.setDataInicio(req.dataInicio());
        contrato.setDataFim(req.dataFim());
        contrato.setEmpresa(empresa);

        return repository.save(contrato);
    }

    public Contrato atualizar(Long id, ContratoRequest req) {
        Contrato contrato = buscar(id);

        contrato.setTitulo(req.titulo());
        contrato.setDescricao(req.descricao());
        contrato.setDataInicio(req.dataInicio());
        contrato.setDataFim(req.dataFim());

        return repository.save(contrato);
    }

    public void desativar(Long id) {
        Contrato contrato = buscar(id);
        contrato.setAtivo(false);
        repository.save(contrato);
    }
}


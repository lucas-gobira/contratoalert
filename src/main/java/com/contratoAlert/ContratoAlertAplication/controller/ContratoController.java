package com.contratoAlert.ContratoAlertAplication.controller;

import com.contratoAlert.ContratoAlertAplication.config.AuthContext;
import com.contratoAlert.ContratoAlertAplication.controller.dto.ContratoRequest;
import com.contratoAlert.ContratoAlertAplication.controller.dto.ContratoResponse;
import com.contratoAlert.ContratoAlertAplication.service.ContratoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService service;

    @GetMapping
    public List<ContratoResponse> listar() {
        return service.listar()
                .stream()
                .map(ContratoResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ContratoResponse buscar(@PathVariable Long id) {
        return ContratoResponse.from(service.buscar(id));
    }

    @PostMapping
    public ContratoResponse criar(@RequestBody @Valid ContratoRequest req) {
        return ContratoResponse.from(service.criar(req));
    }

    @PutMapping("/{id}")
    public ContratoResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ContratoRequest req
    ) {
        return ContratoResponse.from(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id) {
        service.desativar(id);
    }
}





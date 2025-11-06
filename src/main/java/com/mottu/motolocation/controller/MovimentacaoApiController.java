package com.mottu.motolocation.controller;

import com.mottu.motolocation.service.MovimentacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoApiController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoApiController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Data
    public static class MovimentacaoApiRequest {
        @NotBlank(message = "O RFID da moto é obrigatório.")
        private String rfid;

        @NotBlank(message = "O código do sensor é obrigatório.")
        private String sensorCodigo;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> registrarMovimentacao(@Valid @RequestBody MovimentacaoApiRequest request) {
        movimentacaoService.registrarMovimentacao(request.getRfid(), request.getSensorCodigo());
        return ResponseEntity.ok().build();
    }
}

package com.mottu.motolocation.controller;

import com.mottu.motolocation.dto.MotoDTO;
import com.mottu.motolocation.dto.MovimentacaoDTO;
import com.mottu.motolocation.service.MotoService;
import com.mottu.motolocation.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motos")
public class MotoApiController {

    private final MotoService motoService;
    private final MovimentacaoService movimentacaoService;

    public MotoApiController(MotoService motoService, MovimentacaoService movimentacaoService) {
        this.motoService = motoService;
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Page<MotoDTO> listarMotos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "placa") String sortBy,
                                     @RequestParam(required = false) String placa) {
        return motoService.listMotos(page, size, sortBy, placa);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<MotoDTO> getMotoById(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.getMotoById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MotoDTO> createMoto(@Valid @RequestBody MotoDTO motoDTO) {
        return ResponseEntity.ok(motoService.createMoto(motoDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MotoDTO> updateMoto(@PathVariable Long id, @Valid @RequestBody MotoDTO motoDTO) {
        return ResponseEntity.ok(motoService.updateMoto(id, motoDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMoto(@PathVariable Long id) {
        motoService.deleteMoto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/movimentacoes")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Page<MovimentacaoDTO> listarMovimentacoes(@PathVariable Long id,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "dataHora") String sortBy) {
        return movimentacaoService.listarMovimentacoesPorMoto(id, page, size, sortBy);
    }
}

package com.mottu.motolocation.controller;

import com.mottu.motolocation.dto.SensorDTO;
import com.mottu.motolocation.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensores")
public class SensorApiController {

    private final SensorService sensorService;

    public SensorApiController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Page<SensorDTO> listarSensores(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "codigo") String sortBy,
                                          @RequestParam(required = false) String codigo) {
        return sensorService.listSensors(page, size, sortBy, codigo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SensorDTO> getSensorById(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.getSensorById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SensorDTO> createSensor(@Valid @RequestBody SensorDTO sensorDTO) {
        return ResponseEntity.ok(sensorService.createSensor(sensorDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SensorDTO> updateSensor(@PathVariable Long id, @Valid @RequestBody SensorDTO sensorDTO) {
        return ResponseEntity.ok(sensorService.updateSensor(id, sensorDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}

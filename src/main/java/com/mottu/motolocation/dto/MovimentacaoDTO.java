package com.mottu.motolocation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentacaoDTO {
    private Long id;
    private Long motoId;
    private Long sensorId;
    private LocalDateTime dataHora;
}
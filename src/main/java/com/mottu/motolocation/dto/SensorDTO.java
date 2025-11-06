package com.mottu.motolocation.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDTO {
    private Long id;

    @NotBlank
    private String codigo;

    private int posicaoX;
    private int posicaoY;
    private String descricao;
}
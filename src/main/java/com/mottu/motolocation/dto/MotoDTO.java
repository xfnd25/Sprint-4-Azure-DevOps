package com.mottu.motolocation.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotoDTO {
    private Long id;

    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    @Min(2020)
    private int ano;

    private String rfidTag;

    private String status;
    private String observacoes;
}
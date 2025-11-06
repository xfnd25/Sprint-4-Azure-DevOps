package com.mottu.motolocation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String placa;

    @NotBlank
    private String modelo;

    @Min(2020)
    private int ano;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String rfidTag;

    private String status;

    private String observacoes;

    // RELACIONAMENTO COM Movimentacao (adicionado)
    @OneToMany(mappedBy = "moto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Movimentacao> movimentacoes = new ArrayList<>();
}
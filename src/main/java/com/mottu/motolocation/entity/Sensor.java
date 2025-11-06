package com.mottu.motolocation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList; // Import adicionado
import java.util.List;      // Import adicionado

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(name = "posicao_x")
    private int posicaoX;

    @Column(name = "posicao_y")
    private int posicaoY;

    private String descricao;

    // RELACIONAMENTO ADICIONADO COM A CONFIGURAÇÃO DE CASCATA
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacao> movimentacoes = new ArrayList<>();
}
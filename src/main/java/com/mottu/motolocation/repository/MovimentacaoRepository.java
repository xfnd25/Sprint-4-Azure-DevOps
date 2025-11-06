package com.mottu.motolocation.repository;

import com.mottu.motolocation.entity.Movimentacao;
import com.mottu.motolocation.entity.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    Page<Movimentacao> findByMoto(Moto moto, Pageable pageable);

    // O método existsBySensor(Sensor sensor) foi removido.
}
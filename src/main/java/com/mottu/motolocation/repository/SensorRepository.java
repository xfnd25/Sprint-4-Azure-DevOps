package com.mottu.motolocation.repository;

import com.mottu.motolocation.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;  // <--- Importar essa interface
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>, JpaSpecificationExecutor<Sensor> {
    Optional<Sensor> findByCodigo(String codigo);
}

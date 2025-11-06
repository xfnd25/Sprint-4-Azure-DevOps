package com.mottu.motolocation.repository;

import com.mottu.motolocation.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.Optional;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long>, JpaSpecificationExecutor<Moto> {
    Optional<Moto> findByPlaca(String placa);
    Optional<Moto> findByRfidTag(String rfidTag);
}

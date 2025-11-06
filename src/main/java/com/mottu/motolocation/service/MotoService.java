package com.mottu.motolocation.service;

import com.mottu.motolocation.dto.MotoDTO;
import com.mottu.motolocation.entity.Moto;
import com.mottu.motolocation.exception.ResourceNotFoundException;
import com.mottu.motolocation.repository.MotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.domain.Specification;
import java.util.UUID;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final ModelMapper modelMapper;

    public MotoService(MotoRepository motoRepository, ModelMapper modelMapper) {
        this.motoRepository = motoRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public MotoDTO createMoto(MotoDTO motoDTO) {
        Moto moto = modelMapper.map(motoDTO, Moto.class);

        // Gera um RFID automático caso não seja informado
        if (moto.getRfidTag() == null || moto.getRfidTag().isBlank()) {
            moto.setRfidTag("RFID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }

        moto = motoRepository.save(moto);
        return modelMapper.map(moto, MotoDTO.class);
    }


    @Cacheable("motoById")
    public MotoDTO getMotoById(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com id: " + id));
        return modelMapper.map(moto, MotoDTO.class);
    }

    @Transactional
    public MotoDTO updateMoto(Long id, MotoDTO motoDTO) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com id: " + id));

        // Atualiza os campos editáveis
        moto.setPlaca(motoDTO.getPlaca());
        moto.setModelo(motoDTO.getModelo());
        moto.setAno(motoDTO.getAno());
        // A linha do rfidTag foi removida para preservar o valor original
        moto.setStatus(motoDTO.getStatus());
        moto.setObservacoes(motoDTO.getObservacoes());

        moto = motoRepository.save(moto);
        return modelMapper.map(moto, MotoDTO.class);
    }

    @Transactional
    public void deleteMoto(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com id: " + id));
        motoRepository.delete(moto);
    }

    @Cacheable("motos")
    public Page<MotoDTO> listMotos(int page, int size, String sortBy, String placaFiltro) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Specification<Moto> spec = (root, query, cb) -> {
            if (placaFiltro == null || placaFiltro.isEmpty()) {
                return cb.conjunction(); // Sem filtro, retorna tudo
            }
            return cb.like(cb.lower(root.get("placa")), "%" + placaFiltro.toLowerCase() + "%");
        };

        Page<Moto> motos = motoRepository.findAll(spec, pageable);

        return motos.map(moto -> modelMapper.map(moto, MotoDTO.class));
    }
}
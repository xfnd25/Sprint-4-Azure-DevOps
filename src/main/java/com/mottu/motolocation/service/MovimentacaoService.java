package com.mottu.motolocation.service;

import com.mottu.motolocation.dto.MovimentacaoDTO;
import com.mottu.motolocation.entity.Movimentacao;
import com.mottu.motolocation.entity.Moto;
import com.mottu.motolocation.entity.Sensor;
import com.mottu.motolocation.exception.ResourceNotFoundException;
import com.mottu.motolocation.repository.MovimentacaoRepository;
import com.mottu.motolocation.repository.MotoRepository;
import com.mottu.motolocation.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final MotoRepository motoRepository;
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, MotoRepository motoRepository,
                               SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.motoRepository = motoRepository;
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MovimentacaoDTO registrarMovimentacao(String rfid, String sensorCodigo) {
        Moto moto = motoRepository.findByRfidTag(rfid)
                .orElseThrow(() -> new ResourceNotFoundException("Moto com RFID " + rfid + " não encontrada"));

        Sensor sensor = sensorRepository.findByCodigo(sensorCodigo)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor com código " + sensorCodigo + " não encontrado"));

        Movimentacao mov = Movimentacao.builder()
                .moto(moto)
                .sensor(sensor)
                .build();

        movimentacaoRepository.save(mov);

        return modelMapper.map(mov, MovimentacaoDTO.class);
    }

    @Cacheable("movimentacoesPorMoto")
    public Page<MovimentacaoDTO> listarMovimentacoesPorMoto(Long motoId, int page, int size, String sortBy) {
        Moto moto = motoRepository.findById(motoId)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com id: " + motoId));

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Movimentacao> pageMov = movimentacaoRepository.findByMoto(moto, pageable);

        return pageMov.map(mov -> modelMapper.map(mov, MovimentacaoDTO.class));
    }
}

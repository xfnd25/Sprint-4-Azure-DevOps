package com.mottu.motolocation.service;

import com.mottu.motolocation.dto.SensorDTO;
import com.mottu.motolocation.entity.Sensor;
import com.mottu.motolocation.exception.ResourceNotFoundException;
import com.mottu.motolocation.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Removemos os imports que não são mais necessários (MovimentacaoRepository, ResponseStatusException, etc.)

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    // O construtor volta ao original, sem o MovimentacaoRepository
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public SensorDTO createSensor(SensorDTO sensorDTO) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        sensor = sensorRepository.save(sensor);
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @Cacheable("sensorById")
    public SensorDTO getSensorById(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @Transactional
    public SensorDTO updateSensor(Long id, SensorDTO sensorDTO) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));

        sensor.setCodigo(sensorDTO.getCodigo());
        sensor.setPosicaoX(sensorDTO.getPosicaoX());
        sensor.setPosicaoY(sensorDTO.getPosicaoY());
        sensor.setDescricao(sensorDTO.getDescricao());

        sensor = sensorRepository.save(sensor);
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @Transactional
    public void deleteSensor(Long id) {
        // A lógica de verificação foi removida.
        // Agora, apenas buscamos e deletamos o sensor.
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        sensorRepository.delete(sensor);
    }

    @Cacheable("sensors")
    public Page<SensorDTO> listSensors(int page, int size, String sortBy, String codigoFiltro) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Sensor> sensors;
        if (codigoFiltro != null && !codigoFiltro.isEmpty()) {
            sensors = sensorRepository.findAll((root, query, cb) ->
                    cb.like(root.get("codigo"), "%" + codigoFiltro + "%"), pageable);
        } else {
            sensors = sensorRepository.findAll(pageable);
        }

        return sensors.map(sensor -> modelMapper.map(sensor, SensorDTO.class));
    }
}
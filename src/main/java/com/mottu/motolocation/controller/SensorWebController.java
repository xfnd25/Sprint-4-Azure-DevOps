package com.mottu.motolocation.controller;

import com.mottu.motolocation.dto.SensorDTO;
import com.mottu.motolocation.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/sensores")
public class SensorWebController {

    private final SensorService sensorService;

    public SensorWebController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public String listarSensores(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {
        Page<SensorDTO> sensoresPage = sensorService.listSensors(page, size, "codigo", null);
        model.addAttribute("sensoresPage", sensoresPage);
        return "sensores/list"; // Aponta para a nossa nova view
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sensorDTO", new SensorDTO());
        return "sensores/form";
    }

    @PostMapping
    public String createSensor(@Valid @ModelAttribute("sensorDTO") SensorDTO sensorDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "sensores/form";
        }
        sensorService.createSensor(sensorDTO);
        return "redirect:/web/sensores";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SensorDTO sensorDTO = sensorService.getSensorById(id);
        model.addAttribute("sensorDTO", sensorDTO);
        return "sensores/form";
    }

    @PostMapping("/{id}")
    public String updateSensor(@PathVariable Long id, @Valid @ModelAttribute("sensorDTO") SensorDTO sensorDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "sensores/form";
        }
        sensorService.updateSensor(id, sensorDTO);
        return "redirect:/web/sensores";
    }

    @GetMapping("/delete/{id}")
    public String deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return "redirect:/web/sensores";
    }
}
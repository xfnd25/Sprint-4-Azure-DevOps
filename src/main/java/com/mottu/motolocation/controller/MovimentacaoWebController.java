package com.mottu.motolocation.controller;

import com.mottu.motolocation.service.MovimentacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/movimentacoes")
public class MovimentacaoWebController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoWebController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    // DTO specifically for the web form
    @Data
    public static class MovimentacaoForm {
        @NotBlank(message = "O RFID da moto é obrigatório.")
        private String rfid;

        @NotBlank(message = "O código do sensor é obrigatório.")
        private String sensorCodigo;
    }

    // Method to display the registration form
    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("movimentacaoForm", new MovimentacaoForm());
        return "movimentacoes/form";
    }

    // Method to process the form submission
    @PostMapping
    public String registrarMovimentacao(@Valid @ModelAttribute("movimentacaoForm") MovimentacaoForm form,
                                        RedirectAttributes redirectAttributes) {
        try {
            movimentacaoService.registrarMovimentacao(form.getRfid(), form.getSensorCodigo());
            redirectAttributes.addFlashAttribute("successMessage", "Movimentação registrada com sucesso!");
            // Redirects back to the form for a new entry
            return "redirect:/web/movimentacoes/new";
        } catch (Exception e) {
            // If an error occurs (e.g., RFID not found), show it on the form
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/web/movimentacoes/new";
        }
    }
}
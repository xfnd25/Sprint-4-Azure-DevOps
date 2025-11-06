package com.mottu.motolocation.controller;

import com.mottu.motolocation.dto.MotoDTO;
import com.mottu.motolocation.service.MotoService;
import com.mottu.motolocation.dto.MovimentacaoDTO;
import com.mottu.motolocation.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
// ATENÇÃO: Removi o @RequestMapping daqui para podermos adicionar a rota raiz "/"
public class MotoWebController {

    private final MotoService motoService;
    private final MovimentacaoService movimentacaoService;

    public MotoWebController(MotoService motoService, MovimentacaoService movimentacaoService) {
        this.motoService = motoService;
        this.movimentacaoService = movimentacaoService;
    }

    // NOVO MÉTODO PARA A PÁGINA INICIAL (RAIZ)
    // Ele redireciona qualquer acesso a "/" para a nossa lista de motos.
    @GetMapping("/")
    public String index() {
        return "redirect:/web/motos";
    }

    @GetMapping("/web/motos")
    public String listarMotos(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        Page<MotoDTO> motosPage = motoService.listMotos(page, size, "placa", null);
        model.addAttribute("motosPage", motosPage);
        return "motos/list";
    }

    @GetMapping("/web/motos/new")
    public String showCreateForm(Model model) {
        model.addAttribute("motoDTO", new MotoDTO());
        return "motos/form";
    }

    @PostMapping("/web/motos")
    public String createMoto(@Valid @ModelAttribute("motoDTO") MotoDTO motoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "motos/form";
        }
        motoService.createMoto(motoDTO);
        return "redirect:/web/motos";
    }

    @GetMapping("/web/motos/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        MotoDTO motoDTO = motoService.getMotoById(id);
        model.addAttribute("motoDTO", motoDTO);
        return "motos/form";
    }

    @PostMapping("/web/motos/{id}")
    public String updateMoto(@PathVariable Long id, @Valid @ModelAttribute("motoDTO") MotoDTO motoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "motos/form";
        }
        motoService.updateMoto(id, motoDTO);
        return "redirect:/web/motos";
    }

    @GetMapping("/web/motos/delete/{id}")
    public String deleteMoto(@PathVariable Long id) {
        motoService.deleteMoto(id);
        return "redirect:/web/motos";
    }

    @GetMapping("/web/motos/{id}/movimentacoes")
    public String listarMovimentacoes(@PathVariable Long id,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      Model model) {
        MotoDTO moto = motoService.getMotoById(id);
        Page<MovimentacaoDTO> movimentacoesPage = movimentacaoService.listarMovimentacoesPorMoto(id, page, size, "dataHora");

        model.addAttribute("moto", moto);
        model.addAttribute("movimentacoesPage", movimentacoesPage);
        return "movimentacoes/list";
    }
}
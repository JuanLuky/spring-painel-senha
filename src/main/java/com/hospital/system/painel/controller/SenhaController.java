package com.hospital.system.painel.controller;

import com.hospital.system.painel.dto.SenhaDTO;
import com.hospital.system.painel.service.SenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/senhas")
public class SenhaController {

    private final SenhaService senhaService;

    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @PostMapping("/chamar/{pacienteId}")
    public ResponseEntity<SenhaDTO> chamarPacientes(
            @PathVariable Long pacienteId,
            @RequestParam Long consultorioId) {
        return ResponseEntity.ok(senhaService.chamarPaciente(pacienteId, consultorioId));
    }

    @GetMapping("/listar-senhas-chamadas")
    public ResponseEntity<List<SenhaDTO>> listarChamadas() {
        return ResponseEntity.ok(senhaService.listarSenhasChamadas());
    }

//    @GetMapping("/listar-senhas-nao-chamadas")
//    public  ResponseEntity<List<SenhaDTO>> listarSenhasNaoChamadas() {
//        return ResponseEntity.ok(senhaService.listarSenhasNaochamadas());
//    }
}

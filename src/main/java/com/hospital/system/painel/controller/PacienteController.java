package com.hospital.system.painel.controller;

import com.hospital.system.painel.dto.PacienteCreateDTO;
import com.hospital.system.painel.dto.PacienteDTO;
import com.hospital.system.painel.entity.Paciente;
import com.hospital.system.painel.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> cadastrarPaciente(@RequestBody PacienteCreateDTO dto) {
        return ResponseEntity.ok(pacienteService.cadastrarPaciente(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientes(@RequestParam Long id) {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPacienteComSenhas(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/proximo")
//    public ResponseEntity<PacienteDTO> chamarProximoPaciente() {
//        Optional<PacienteDTO> proximo = pacienteService.buscarProximoPaciente();
//        return proximo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
//    }


}

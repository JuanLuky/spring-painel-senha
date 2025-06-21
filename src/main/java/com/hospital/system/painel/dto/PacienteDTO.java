package com.hospital.system.painel.dto;

import com.hospital.system.painel.enums.StatusPaciente;

import java.time.LocalDateTime;

public record PacienteDTO(
        Long id,
        String nome,
        boolean prioridade,
        StatusPaciente status,
        LocalDateTime dataCadastro

) {
}

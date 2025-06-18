package com.hospital.system.painel.dto;

import java.time.LocalDateTime;

public record PacienteDTO(
        Long id,
        String nome,
        boolean prioridade,
        LocalDateTime dataCadastro
) {
}

package com.hospital.system.painel.dto;

import com.hospital.system.painel.enums.StatusPaciente;

public record PacienteCreateDTO(
        Long id,
        String nome,
        boolean prioridade
) {}


package com.hospital.system.painel.dto;

import com.hospital.system.painel.enums.StatusPaciente;

public record PacienteCreateDTO(
        String nome,
        String cpf,
        boolean prioridade
) {}


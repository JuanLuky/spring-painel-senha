package com.hospital.system.painel.dto;

import com.hospital.system.painel.enums.StatusPaciente;

public record AtualizarStatusPacienteDTO(
        StatusPaciente status
) {
}

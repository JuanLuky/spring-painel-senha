package com.hospital.system.painel.dto;

import com.hospital.system.painel.enums.StatusConsultorio;

public record AtualizarStatusConsultorioDTO(
        StatusConsultorio status
) {
}

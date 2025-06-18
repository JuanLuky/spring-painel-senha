package com.hospital.system.painel.dto;

import com.hospital.system.painel.enums.StatusConsultorio;

public record ConsultorioDTO(
        Long id,
        String nome,
        StatusConsultorio status
) {

}

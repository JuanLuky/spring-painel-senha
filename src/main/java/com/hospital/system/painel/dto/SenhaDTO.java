package com.hospital.system.painel.dto;

import java.time.LocalDateTime;

public record SenhaDTO(
        Long id,
        String nomePaciente,
        String nomeConsultorio,
        boolean chamado,
        LocalDateTime dataHora
) { }

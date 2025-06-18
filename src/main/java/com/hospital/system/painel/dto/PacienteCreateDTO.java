package com.hospital.system.painel.dto;

public record PacienteCreateDTO(
        Long id,
        String nome,
        boolean prioridade
) {}


package com.hospital.system.painel.dto;

import com.hospital.system.painel.entity.Paciente;
import com.hospital.system.painel.enums.StatusPaciente;

import java.time.LocalDateTime;

public record PacienteDTO(
        Long id,
        String nome,
        String cpf,
        boolean prioridade,
        StatusPaciente status,
        LocalDateTime dataCadastro

) {
    public PacienteDTO(Paciente pacienteSalvo) {
        this(
                pacienteSalvo.getId(),
                pacienteSalvo.getNome(),
                pacienteSalvo.getCpf(),
                pacienteSalvo.isPrioridade(),
                pacienteSalvo.getStatus(),
                pacienteSalvo.getDataCadastro()
        );
    }
}

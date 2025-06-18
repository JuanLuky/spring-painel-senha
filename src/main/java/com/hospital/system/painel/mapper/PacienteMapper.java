package com.hospital.system.painel.mapper;

import com.hospital.system.painel.dto.PacienteDTO;
import com.hospital.system.painel.entity.Paciente;

public class PacienteMapper {
    public static PacienteDTO toDTO(Paciente paciente) {
        if (paciente == null) return null;

        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.isPrioridade(),
                paciente.getDataCadastro()
        );
    }
}

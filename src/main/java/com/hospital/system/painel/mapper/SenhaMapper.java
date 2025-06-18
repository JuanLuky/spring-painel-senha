package com.hospital.system.painel.mapper;

import com.hospital.system.painel.dto.SenhaDTO;
import com.hospital.system.painel.entity.Senha;

public class SenhaMapper {

    public static SenhaDTO toDTO(Senha senha) {
        if (senha == null) return null;

        return new SenhaDTO(
                senha.getId(),                                // ← getter da entidade
                senha.getPaciente().getNome(),                // ← acessa nome da entidade paciente
                senha.getConsultorio().getNome(),
                senha.isChamado(),
                senha.getDataHora()
        );
    }
}

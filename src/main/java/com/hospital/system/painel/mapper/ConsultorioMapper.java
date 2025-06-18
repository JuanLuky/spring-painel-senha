package com.hospital.system.painel.mapper;

import com.hospital.system.painel.dto.ConsultorioDTO;
import com.hospital.system.painel.entity.Consultorio;

public class ConsultorioMapper {

    public static ConsultorioDTO toDTO(Consultorio c) {
        if (c == null) return null;

        return new ConsultorioDTO(
                c.getId(),
                c.getNome(),
                c.getStatus()
        );

    }
}

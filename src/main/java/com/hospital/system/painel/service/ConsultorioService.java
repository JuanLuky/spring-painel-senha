package com.hospital.system.painel.service;

import com.hospital.system.painel.dto.ConsultorioDTO;
import com.hospital.system.painel.entity.Consultorio;
import com.hospital.system.painel.enums.StatusConsultorio;
import com.hospital.system.painel.mapper.ConsultorioMapper;
import com.hospital.system.painel.repository.ConsultorioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultorioService {

    private final ConsultorioRepository consultorioRepository;

    public ConsultorioService(ConsultorioRepository consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }

    public ConsultorioDTO cadastrarConsultorio(@NotNull ConsultorioDTO dto) {
        Consultorio consultorio = new Consultorio();
        consultorio.setId(dto.id());
        consultorio.setNome(dto.nome());
        consultorio.setStatus(dto.status());

        Consultorio savedConsultorio = consultorioRepository.save(consultorio);
        return ConsultorioMapper.toDTO(savedConsultorio);

    }

    public List<ConsultorioDTO> listarConsultorios() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Consultorio> consultorios = consultorioRepository.findAll(sort);
        return consultorios.stream()
                .map(ConsultorioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ConsultorioDTO atualizarStatus(Long id, String status) {
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultório não encontrado"));

        consultorio.setStatus(StatusConsultorio.valueOf(status)); // Enum com DISPONIVEL ou OCUPADO
        consultorioRepository.save(consultorio);

        return ConsultorioMapper.toDTO(consultorio);
    }


}

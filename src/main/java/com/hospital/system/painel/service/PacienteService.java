package com.hospital.system.painel.service;


import com.hospital.system.painel.dto.PacienteCreateDTO;
import com.hospital.system.painel.dto.PacienteDTO;
import com.hospital.system.painel.entity.Consultorio;
import com.hospital.system.painel.entity.Paciente;
import com.hospital.system.painel.entity.Senha;
import com.hospital.system.painel.enums.StatusConsultorio;
import com.hospital.system.painel.enums.StatusPaciente;
import com.hospital.system.painel.mapper.PacienteMapper;
import com.hospital.system.painel.repository.ConsultorioRepository;
import com.hospital.system.painel.repository.PacienteRepository;
import com.hospital.system.painel.repository.SenhaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final SenhaRepository senhaRepository;
    private final ConsultorioRepository consultorioRepository;

    public PacienteService(PacienteRepository pacienteRepository, SenhaRepository senhaRepository, ConsultorioRepository consultorioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.senhaRepository = senhaRepository;
        this.consultorioRepository = consultorioRepository;
    }

    public PacienteDTO cadastrarPaciente(@NotNull PacienteCreateDTO dto) {
        // Aqui você pode adicionar a lógica para cadastrar o paciente
        Paciente paciente = new Paciente();
        paciente.setId(dto.id());
        paciente.setNome(dto.nome());
        paciente.setPrioridade(dto.prioridade());

        // Definir o status do paciente como Não Atendido
        paciente.setStatus(StatusPaciente.NAO_ATENDIDO);

        // Salvar o paciente no banco de dados
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        // Retornar o paciente salvo como DTO
        return PacienteMapper.toDTO(pacienteSalvo);
    }

    @Transactional
    public void excluirPacienteComSenhas(Long pacienteId) {
        // 1. Carrega o paciente com as senhas
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        // 2. Encontra o consultório ocupado associado às senhas do paciente
        Optional<Consultorio> consultorioOcupado = paciente.getSenhas().stream()
                .filter(Objects::nonNull)
                .map(Senha::getConsultorio)
                .filter(c -> c.getStatus() == StatusConsultorio.OCUPADO)
                .findFirst();

        // 3. Remove todas as senhas do paciente
        senhaRepository.deleteAll(paciente.getSenhas());

        // 4. Se encontrou consultório ocupado, libera ele
        consultorioOcupado.ifPresent(consultorio -> {
            consultorio.setStatus(StatusConsultorio.DISPONIVEL);
            consultorioRepository.save(consultorio);
        });

        // 5. Exclui o paciente
        pacienteRepository.delete(paciente);
    }

    public List<PacienteDTO> listarPacientes() {

        List<Paciente> pacientes = pacienteRepository.findAllByOrderByDataCadastroAsc(); // Ordenado!
        // Mapear a lista de Paciente para PacienteDTO
        return pacientes.stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO buscarPaciente(Long id) {
        // Aqui você pode adicionar a lógica para buscar o paciente pelo ID
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        // Retornar o paciente encontrado como DTO
        return PacienteMapper.toDTO(paciente);
    }

//    public Optional<PacienteDTO> buscarProximoPaciente() {
//        return pacienteRepository.findFirstByOrderByPrioridadeDescDataCadastroAsc()
//                .map(PacienteMapper::toDTO);
//    }

}

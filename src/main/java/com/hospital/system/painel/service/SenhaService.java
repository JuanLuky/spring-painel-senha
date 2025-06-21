package com.hospital.system.painel.service;

import com.hospital.system.painel.config.websocket.SenhaWebSocketController;
import com.hospital.system.painel.dto.SenhaDTO;
import com.hospital.system.painel.entity.Consultorio;
import com.hospital.system.painel.entity.Paciente;
import com.hospital.system.painel.entity.Senha;
import com.hospital.system.painel.enums.StatusConsultorio;
import com.hospital.system.painel.enums.StatusPaciente;
import com.hospital.system.painel.mapper.SenhaMapper;
import com.hospital.system.painel.repository.ConsultorioRepository;
import com.hospital.system.painel.repository.PacienteRepository;
import com.hospital.system.painel.repository.SenhaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SenhaService {

    private final SenhaRepository senhaRepository;
    private final PacienteRepository pacienteRepository;
    private final ConsultorioRepository consultorioRepository;
    private final SenhaWebSocketController senhaWebSocketController;

    public SenhaService(SenhaRepository senhaRepository, PacienteRepository pacienteRepository, ConsultorioRepository consultorioRepository, SenhaWebSocketController senhaWebSocketController) {
        this.senhaRepository = senhaRepository;
        this.pacienteRepository = pacienteRepository;
        this.consultorioRepository = consultorioRepository;
        this.senhaWebSocketController = senhaWebSocketController;
    }

    public SenhaDTO chamarPaciente(Long pacienteid)  {

        Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteid);
        boolean pacienteJaChamado = senhaRepository.existsByPacienteIdAndChamadoTrue(pacienteid);

        if (pacienteJaChamado) {
            throw new RuntimeException("Este paciente já foi chamado e está em atendimento.");
        }

        if(pacienteOpt.isEmpty()) {
            throw new RuntimeException("Paciente não encontrado");
        }

        // buscar um consultório ativo e disponível
        List<Consultorio> consultorios = consultorioRepository.findAll();
        Optional<Consultorio> consultorioDisponivel = consultorios.stream()
                .filter(c -> c.getStatus() == StatusConsultorio.DISPONIVEL)
                .findFirst();

        if(consultorioDisponivel.isEmpty()) {
            throw new RuntimeException("Nenhum consultório disponível");
        }

        // Criar uma senha
        Senha senha = new Senha();
        senha.setId(senha.getId());
        senha.setPaciente(pacienteOpt.get());
        senha.setConsultorio(consultorioDisponivel.get());
        senha.setChamado(true);
        senha.setDataHora(LocalDateTime.now());

        // Atualiza status do consultório para OCUPADO
        Consultorio consultorio = consultorioDisponivel.get();
        consultorio.setStatus(StatusConsultorio.OCUPADO);
        consultorioRepository.save(consultorio); // <- salva a mudança de status

        // Atualiza o status do paciente para EM_ATENDIMENTO
        Paciente paciente = pacienteOpt.get();
        paciente.setStatus(StatusPaciente.EM_ATENDIMENTO);
        pacienteRepository.save(paciente); // <- salva a mudança de status

        // Salvar a senha no banco de dados
        Senha senhaSalva = senhaRepository.save(senha);

        // Enviar a senha para o painel via WebSocket
        senhaWebSocketController.enviarSenhaParaPainel(SenhaMapper.toDTO(senhaSalva));

        // Retornar a senha salva como DTO
        return SenhaMapper.toDTO(senhaSalva);
    }

    public List<SenhaDTO> listarSenhasChamadas() {
        List<Senha> senhasChamadas = senhaRepository.findByChamadoTrue();
        return senhasChamadas.stream()
                .filter(Senha::isChamado)
                .map(SenhaMapper::toDTO)
                .toList();
    }

    public List<SenhaDTO> listarSenhasNaochamadas() {
        List<Senha> senhas = senhaRepository.findByChamadoFalse();
        return senhas.stream()
                .filter(s -> !s.isChamado())
                .map(SenhaMapper::toDTO)
                .toList();
    }
}

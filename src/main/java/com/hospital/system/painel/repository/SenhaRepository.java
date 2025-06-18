package com.hospital.system.painel.repository;

import com.hospital.system.painel.entity.Senha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SenhaRepository extends JpaRepository<Senha, Long> {
    // Para buscar todas as senhas que já foram chamadas
    List<Senha> findByChamadoTrue();

    // Buscar senhas ainda não chamadas (fila de espera)
    List<Senha> findByChamadoFalse();

    // Verifica se existe senha chamada para o paciente
    boolean existsByPacienteIdAndChamadoTrue(Long pacienteId);
}

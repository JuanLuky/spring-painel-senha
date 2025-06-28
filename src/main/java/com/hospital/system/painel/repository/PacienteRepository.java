package com.hospital.system.painel.repository;

import com.hospital.system.painel.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findFirstByOrderByPrioridadeDescDataCadastroAsc();


    List<Paciente> existsByNomeIgnoreCase(String nome);

    Optional<Paciente> findByCpf(String cpf);


    // Ordena por dataCadastro em ordem DESCENDENTE (mais recente primeiro)
    List<Paciente> findAllByOrderByDataCadastroAsc();
}

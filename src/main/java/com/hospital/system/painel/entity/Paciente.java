package com.hospital.system.painel.entity;

import com.hospital.system.painel.dto.PacienteCreateDTO;
import com.hospital.system.painel.enums.StatusPaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private boolean prioridade; // indica se o paciente tem prioridade

    // Status do paciente, aguardando, em atendimento, etc.
    @Enumerated(EnumType.STRING)
    @Column(name = "status_paciente")
    private StatusPaciente status;

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    // Relacionamento com Senha
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Senha> senhas = new ArrayList<>();

    // Garante que a data seja definida mesmo sem @CreationTimestamp
    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Paciente(PacienteCreateDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.prioridade = dto.prioridade();
        this.status = StatusPaciente.NAO_ATENDIDO;
    }
}

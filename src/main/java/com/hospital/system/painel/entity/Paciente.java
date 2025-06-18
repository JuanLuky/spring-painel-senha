package com.hospital.system.painel.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long id;

    private String nome;

    private boolean prioridade; // indica se o paciente tem prioridade

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    // Relacionamento com Senha
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Senha> senhas = new ArrayList<>();


    public Paciente() {
        this.dataCadastro = LocalDateTime.now();

        // Inicializa a prioridade como falsa
        this.prioridade = false;
    }

    // Garante que a data seja definida mesmo sem @CreationTimestamp
    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
    }
}

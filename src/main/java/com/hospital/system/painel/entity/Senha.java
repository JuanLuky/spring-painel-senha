package com.hospital.system.painel.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_senha")
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;

    private boolean chamado;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public Senha() {
        this.chamado = false;
        this.dataHora = LocalDateTime.now();
    }

}

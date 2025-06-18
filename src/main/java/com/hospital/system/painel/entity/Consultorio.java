package com.hospital.system.painel.entity;

import com.hospital.system.painel.enums.StatusConsultorio;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "tb_consultorio")
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultorio_id")
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusConsultorio status;


    public Consultorio() {

    }


}

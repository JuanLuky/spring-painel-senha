package com.hospital.system.painel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;

    public ErrorResponseDTO(int status, String error, String mensagem) {
        this.status = status;
        this.error = error;
        this.message = mensagem;
    }
}

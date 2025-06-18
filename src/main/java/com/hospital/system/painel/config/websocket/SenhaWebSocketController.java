package com.hospital.system.painel.config.websocket;

import com.hospital.system.painel.dto.SenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SenhaWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void enviarSenhaParaPainel(SenhaDTO senhaDTO) {
        // Envia a senha para todos que estiverem conectados no tópico
        messagingTemplate.convertAndSend("/topic/senha", senhaDTO);
    }
}

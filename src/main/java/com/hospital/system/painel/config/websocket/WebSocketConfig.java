package com.hospital.system.painel.config.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

     // Configurações do WebSocket

    @Override
     public void configureMessageBroker(MessageBrokerRegistry config) {
         config.enableSimpleBroker("/topic"); // Define que mensagens serão enviadas para quem "escutar" o /topic
         config.setApplicationDestinationPrefixes("/app"); // Tudo que o cliente enviar vai ser prefixado com /app
     }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Define o endpoint para conexão
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:4200, https://system-hospital.vercel.app/") // Permite o acesso do frontend
                .withSockJS(); // Define o endpoint para conexão
    }

}

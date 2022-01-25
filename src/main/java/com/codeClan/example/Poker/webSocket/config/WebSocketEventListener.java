//package com.codeClan.example.Poker.webSocket.config;
//
//import com.codeclan.example.websocketsTutorial.model.MessageType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionConnectedEvent;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
//@Component
//public class WebSocketEventListener {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);
//
//    @Autowired
//    private SimpMessageSendingOperations sendingOperations;
//
//    @EventListener
//    public void handleWebSocketConnectListener(final SessionConnectedEvent event){
//        LOGGER.info(" We have a new  connection");
//    }
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event){
//
//        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        final String userName = (String) headerAccessor.getSessionAttributes().get("userName");
//
//        final com.codeclan.example.websocketsTutorial.model.ChatMessage chatMessage = com.codeclan.example.websocketsTutorial.model.ChatMessage.builder()
//                .type(MessageType.DISCONNECT)
//                .sender(userName)
//                .build();
//        sendingOperations.convertAndSend("/topic/public",chatMessage);
//    }
//}

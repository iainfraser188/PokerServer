//package com.codeClan.example.Poker.webSocket.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatController {
//    @MessageMapping("/chat.send")
//    @SendTo("/topic/public")
//    public com.codeclan.example.websocketsTutorial.model.ChatMessage sendMessage(@Payload final com.codeclan.example.websocketsTutorial.model.ChatMessage chatMessage){
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.newUser")
//    @SendTo("/topic/public")
//    public com.codeclan.example.websocketsTutorial.model.ChatMessage newUser(@Payload final com.codeclan.example.websocketsTutorial.model.ChatMessage chatMessage,
//                                                                             SimpMessageHeaderAccessor headerAccessor){
//        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
//        return chatMessage;
//    }
//}

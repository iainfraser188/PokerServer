package com.codeClan.example.Poker.webSocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.io.Console;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("HelloMessage message: " + message);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

//    @MessageMapping("/chat.newUser")
//    @SendTo("/topic/public")
//    public ChatMessage newUser(@Payload final ChatMessage chatMessage,
//                               SimpMessageHeaderAccessor headerAccessor){
//        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
//        return chatMessage;
//    }




}

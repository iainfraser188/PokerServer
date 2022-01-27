package com.codeClan.example.Poker.webSocketTesting.controller;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ChatMessage {

    @Getter
    private com.codeclan.example.websocketsTutorial.model.MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time;
}

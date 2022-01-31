package com.codeClan.example.Poker.webSockets;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/game")
public class WebSocketConfig {

    private Session session;

    @OnOpen
    public void onCreateSession(Session session){
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.session != null && this.session.isOpen()){
            try {
                this.session.getBasicRemote().sendText("from server:" + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

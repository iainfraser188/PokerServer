package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.game.models.Card;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import com.codeClan.example.Poker.webSocket.models.PlayerAction;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayerWebSocketController {

    // test route
    @MessageMapping("/hello")
    @SendTo("/client/greetings")
    public Player player(Player userInputPlayer) throws Exception {
        System.out.println(userInputPlayer.getName());
        System.out.println(userInputPlayer.getUsername());
        System.out.println("heeloooo");
        return new Player("Tom", 100.00, "Tom76", "123");
    }

    @MessageMapping("/action/game/{id}")
//    @SendTo("client/greetings")
    public void handlePlayerAction(@DestinationVariable long id, PlayerAction playerAction) throws Exception {
        String action = playerAction.getAction();
        long playerId = playerAction.getPlayerId();
        double betAmount = playerAction.getBetAmount();
        // test
        System.out.println(id);
        System.out.println(playerId);
        System.out.println(action);
        System.out.println(betAmount);
        // pass this data into the game logic...
//        List<Player> players= new ArrayList<>();
////        List<Card> board= new ArrayList<>();
////        SimpMessagingTemplate sender = new SimpMessagingTemplate();
//        sender.convertAndSend("/client/game/" + id, new GameTable(200.0, players, board, 5.0 ));
    }



}


//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

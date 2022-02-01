package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Deck;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import com.codeClan.example.Poker.game.models.game.Dealer;
import com.codeClan.example.Poker.webSocket.models.PlayerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PlayerWebSocketController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameTableRepository gameTableRepository;

    // CREATE GAME
    @MessageMapping("/create/game/{id}")
    @SendTo("/client/greetings")
    public GameTable gameTable(Player user, @DestinationVariable long id) throws Exception {
        // call user repository
        Player player = playerRepository.findById(user.getId()).get();
        System.out.println("player" + player);
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(player));
        Deck deck = new Deck();
        GameTable gameTable = new GameTable(0.0, players, user.getBigBlindValue(), deck);
//        System.out.println("gametable" + gameTable.getId());
        gameTableRepository.save(gameTable);
        player.setGame_table(gameTable);
        playerRepository.save(player);
        System.out.println("created game. User: " + player.getUsername() + ". GameTable: " + gameTable.getId());
        return gameTable;
    }

    // JOIN GAME
    @MessageMapping("/game/{id}")
    @SendTo("/client/greetings")
    public GameTable joinGameTable(Player user, @DestinationVariable long id) throws Exception {
        // call user repository
        Optional<Player> player = playerRepository.findById(user.getId());
        System.out.println(player);
        Optional<GameTable> gameTable = gameTableRepository.findById(id);
        gameTable.get().addPlayer(player.get());
        System.out.println("Joining game. User: " + player.get().getUsername() + ". GameTable: " + gameTable.get().getId());
        return gameTable.get();
    }

    @MessageMapping("/action/game/{id}")
//    @SendTo("client/greetings")
    public void handlePlayerAction(@DestinationVariable long id, PlayerAction playerAction) throws Exception {
        String action = playerAction.getAction();
        long playerId = playerAction.getPlayerId();
        double betAmount = playerAction.getBetAmount();
        // test
        System.out.println("Player action: ");
        System.out.println(id);
        System.out.println(playerId);
        System.out.println(action);
        System.out.println(betAmount);

        if(playerAction.getAction() == "deal") {
            GameTable table = gameTableRepository.getById(id);
            Dealer dealer = new Dealer(table);
            dealer.dealHoleCards();
        }

        if(playerAction.getAction() == "bet" || playerAction.getAction() == "call") {
            GameTable table = gameTableRepository.getById(id);
            List<Player> players = table.getPlayers();
            Player tempPlayer = new Player();
            double amount = playerAction.getBetAmount();
            for(Player player : players) {
                if(player.isActive()) {
                    tempPlayer = player;
                }
            }
            tempPlayer.bet(amount);
        }

        if(playerAction.getAction() == "fold") {
            GameTable table = gameTableRepository.getById(id);
            List<Player> players = table.getPlayers();
            Player tempPlayer = new Player();
            double amount = playerAction.getBetAmount();
            for(Player player : players) {
                if(player.isActive()) {
                    tempPlayer = player;
                }
            }
            tempPlayer.fold();
        }
        /*
 pass this data into the game logic...
        List<Player> players= new ArrayList<>();
//        List<Card> board= new ArrayList<>();
//        SimpMessagingTemplate sender = new SimpMessagingTemplate();
        sender.convertAndSend("/client/game/" + id, new GameTable(200.0, players, board, 5.0 ));
*/
    }



}


//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

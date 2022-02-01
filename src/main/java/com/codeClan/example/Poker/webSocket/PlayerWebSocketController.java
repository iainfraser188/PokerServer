package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Deck;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import com.codeClan.example.Poker.game.models.game.Dealer;
import com.codeClan.example.Poker.game.models.game.bettingRound.PreFlopBetting;
import com.codeClan.example.Poker.webSocket.models.PlayerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @MessageMapping("/create/game/{gameKey}")
    @SendTo("/client/greetings")

    public ResponseEntity<GameTable> gameTable(Player user, @DestinationVariable String gameKey) throws Exception {
        // check if gameKey already exists
        Optional<GameTable> checkIfExists = gameTableRepository.findGameTableByGameKey(gameKey);
        if (checkIfExists.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            Player player = playerRepository.findById(user.getId()).get();
            ArrayList<Player> players = new ArrayList<>(Arrays.asList(player));
            Deck deck = new Deck();
            GameTable gameTable = new GameTable(0.0, players, user.getBigBlindValue(), deck);
            gameTable.setGameKey(gameKey);
            gameTableRepository.save(gameTable);
            player.setGame_table(gameTable);
            playerRepository.save(player);
            System.out.println("Created game (key: " + gameKey +". User: " + player.getUsername());
            return new ResponseEntity<>(gameTable, HttpStatus.OK);
        }

    }

    // JOIN GAME
    @MessageMapping("/join/game/{gameKey}")
    @SendTo("/client/join")
    public ResponseEntity<GameTable> joinGameTable(@DestinationVariable String gameKey, Player user) throws Exception {
        System.out.println("INSIDE THE JOIN GAME METHOD"); // test
        // check if table exists...
        Optional<GameTable> gameTableCheck = gameTableRepository.findGameTableByGameKey(gameKey);
        if (gameTableCheck.isPresent()) {
            Player player = playerRepository.findById(user.getId()).get();
            GameTable gameTable = gameTableCheck.get();
            // check player is not already part of the list
            List<Player> currentPlayers = gameTable.getPlayers();
            ArrayList<Long> playerIds = new ArrayList<>();
            currentPlayers.forEach(currentPlayer -> playerIds.add(currentPlayer.getId()));
            if (!playerIds.contains(user.getId())) {
                System.out.println("Player not already at the table...");
                gameTable.addPlayer(player);
            }
            gameTableRepository.save(gameTable);
            player.setGame_table(gameTable);
            playerRepository.save(player);
            System.out.println("Join game (key: " + gameKey +". User: " + player.getUsername());
            return new ResponseEntity<>(gameTable, HttpStatus.OK);
        }
        // if no table is found
        else {
            System.out.println("table is not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/action/game/{id}")
//    @SendTo("client/greetings")
    public void handlePlayerAction(@DestinationVariable long id, PlayerAction playerAction) throws Exception {
//        String action = playerAction.getAction();
//        long playerId = playerAction.getPlayerId();
//        double betAmount = playerAction.getBetAmount();
        // test
        System.out.println(playerAction.toString());
        System.out.println("Player action: ");
        System.out.println(playerAction.getAction());
        System.out.println(playerAction.getBetAmount());
        System.out.println(playerAction.getPlayerId());
//        System.out.println(playerAction);
//        System.out.println(id);
//        System.out.println(playerId);
//        System.out.println(action);
//        System.out.println(betAmount);

        if(playerAction.getAction() == "deal") {
            GameTable table = gameTableRepository.getById(id);
            Dealer dealer = new Dealer(table);
            dealer.dealHoleCards();
            gameTableRepository.save(table);
        }

        System.out.println("Post deal route");
        System.out.println(playerRepository.getById(1L).getHand());
        System.out.println(playerRepository.getById(2L).getHand());

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
            table.addToPot(amount);
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

    }

}

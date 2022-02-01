package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
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
            GameTable gameTable = new GameTable(0.0, players, user.getBigBlindValue());
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
        String action = playerAction.getAction();
        long playerId = playerAction.getPlayerId();
        double betAmount = playerAction.getBetAmount();
        // test
        System.out.println("Player action: ");
        System.out.println(id);
        System.out.println(playerId);
        System.out.println(action);
        System.out.println(betAmount);
    }

}

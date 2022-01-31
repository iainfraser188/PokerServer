package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import com.codeClan.example.Poker.webSocket.models.PlayerAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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
    public GameTable gameTable(Player user, @DestinationVariable String gameKey) throws Exception {
        Player player = playerRepository.findById(user.getId()).get();
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player));
        GameTable gameTable = new GameTable(0.0, players, user.getBigBlindValue());
        gameTable.setGameKey(gameKey);
        gameTableRepository.save(gameTable);
        player.setGame_table(gameTable);
        playerRepository.save(player);
        System.out.println("Created game (key: " + gameKey +". User: " + player.getUsername());
        return gameTable;
    }

    // JOIN GAME
    @MessageMapping("/game/{gameKey}")
    @SendTo("/client/greetings")
    public Optional<GameTable> joinGameTable(Player user, @DestinationVariable String gameKey) throws Exception {
        // check if table exists...
        Optional<GameTable> gameTableCheck = gameTableRepository.findGameTableByGameKey(gameKey);
        if (gameTableCheck.isPresent()) {
            Player player = playerRepository.findById(user.getId()).get();
            GameTable gameTable = gameTableCheck.get();
            gameTable.addPlayer(player);
            gameTableRepository.save(gameTable);
            playerRepository.save(player);
            return Optional.of(gameTable);
        }
        // if no table is found
        else {
            return null;
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

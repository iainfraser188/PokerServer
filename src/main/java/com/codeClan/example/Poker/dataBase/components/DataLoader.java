package com.codeClan.example.Poker.dataBase.components;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.GameTable;
import com.codeClan.example.Poker.game.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameTableRepository gameTableRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args) {

        Player Iain = new Player("iain", 1000, "Iain123", "1234");

        Player Alex = new Player("alex", 1000, "Alex123", "4321");

        Player Stuart = new Player("stuart", 1000, "Stuart123", "2345");

        List<Player> players1 = new ArrayList<>(Arrays.asList(Alex));
        GameTable gameTable2 = new GameTable(0.0, players1, 5.0);

        List<Player> players2 = new ArrayList<>(Arrays.asList(Stuart));
        GameTable table1 = new GameTable(0.00, players2, 100);

        Alex.setGame_table(table1);
        Stuart.setGame_table(gameTable2);

        gameTableRepository.save(gameTable2);
        gameTableRepository.save(table1);

        playerRepository.save(Iain);
        playerRepository.save(Alex);
        playerRepository.save(Stuart);

    }
}

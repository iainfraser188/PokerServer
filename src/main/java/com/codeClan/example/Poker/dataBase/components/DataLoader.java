package com.codeClan.example.Poker.dataBase.components;


import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args){

        Player Iain = new Player("iain",1000,"Iain123","1234");
        playerRepository.save(Iain);
        Player Alex = new Player("alex",1000,"Alex123","4321");
        playerRepository.save(Alex);
        Player Stuart = new Player("stuart",1000,"Stuart123","2345");
        playerRepository.save(Stuart);

    }
}

package com.codeClan.example.Poker.dataBase.controllers;

import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping(value = "/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/players/{id}")
    public ResponseEntity<Optional<Player>>getPlayer(@PathVariable Long id){
        Optional<Player> payload = playerRepository.findById(id);
        if(payload.isPresent()){
            return new ResponseEntity<>(payload,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(payload,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/players")
    public ResponseEntity<Player>postPlayer(@RequestBody Player player){
        playerRepository.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping (value = "/players/{id}")
    public ResponseEntity<Player>updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerToFind = playerRepository.findById(id);
        if (playerToFind.isPresent()) {
            Player playerToUpdate = playerToFind.get();
            playerToUpdate.setName(player.getName());
            playerToUpdate.setStack(player.getStack());
            playerToUpdate.setUsername(player.getUsername());
            playerToUpdate.setPassword(player.getPassword());
            playerRepository.save(playerToUpdate);
            return new ResponseEntity<>(playerToUpdate,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping (value = "/players/{id}/{name}")
    public ResponseEntity<Player>updatePlayerName(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerToFind = playerRepository.findById(id);
        if (playerToFind.isPresent()) {
            Player playerToUpdate = playerToFind.get();
            playerToUpdate.setName(player.getName());
            playerRepository.save(playerToUpdate);
            return new ResponseEntity<>(playerToUpdate,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }





    }

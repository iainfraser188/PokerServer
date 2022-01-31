package com.codeClan.example.Poker.dataBase.controllers;

import com.codeClan.example.Poker.dataBase.repositories.PlayerRepository;
import com.codeClan.example.Poker.game.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Optional<Player>> getPlayer(@PathVariable Long id) {
        Optional<Player> payload = playerRepository.findById(id);
        if (payload.isPresent()) {
            payload.get().setPassword(null);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Optional<Long>> validateLogin(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "password") String password
    ) {
        Optional<Player> player = playerRepository.findPlayerByUsernameAndPassword(username, password);
        if (player.isPresent()) {
            Optional<Long> id = Optional.ofNullable(player.get().getId());
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/players")
    public ResponseEntity<Player> postPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping(value = "/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerToFind = playerRepository.findById(id);
        if (playerToFind.isPresent()) {
            Player playerToUpdate = playerToFind.get();
            playerToUpdate.setName(player.getName());
            playerToUpdate.setStack(player.getStack());
            playerToUpdate.setUsername(player.getUsername());
            playerToUpdate.setPassword(player.getPassword());
            playerRepository.save(playerToUpdate);
            return new ResponseEntity<>(playerToUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/players/{id}")
    public ResponseEntity<Player> updatePlayerValues(@PathVariable Long id, @RequestBody Map<Object, Object> changes) {
        Optional<Player> playerToFind = playerRepository.findById(id);
        if (playerToFind.isPresent()) {
            Player playerToUpdate = playerToFind.get();
            changes.forEach(
                    (change, value) -> {
                        if ("name".equals(change)) {
                            playerToUpdate.setName((String) value);
                        }
                        else if ("userName".equals(change)) {
                            playerToUpdate.setUsername((String) value);

                        }
                        else if ("password".equals(change)) {
                            playerToUpdate.setPassword((String) value);

                        }
                        else if ("stack".equals(change)) {
                            playerToUpdate.setStack((Double) value);

                        }
                        else if ("active".equals(change)){
                            playerToUpdate.setActive((Boolean) value );
                        }
                        else if ("folded".equals(change)) {
                            playerToUpdate.setFolded((Boolean) value);
                        }
                        else if ("contribution".equals(change)){
                            playerToUpdate.setContribution((Double) value);

                        }
                        else if ("bigBlind".equals(change)){
                            playerToUpdate.setBigBlind((Boolean) value);

                        }
                        else if ("smallBlind".equals(change)){
                            playerToUpdate.setSmallBlind((Boolean) value);
                        }
                        playerRepository.save(playerToUpdate);

                    });
            return new ResponseEntity<>(playerToUpdate,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    }

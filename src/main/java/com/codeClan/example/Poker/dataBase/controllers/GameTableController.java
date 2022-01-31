package com.codeClan.example.Poker.dataBase.controllers;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameTableController {

    @Autowired
    GameTableRepository gameTableRepository;


}

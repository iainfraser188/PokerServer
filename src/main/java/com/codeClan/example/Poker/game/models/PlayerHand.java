package com.codeClan.example.Poker.game.models;

import java.util.List;
import java.util.ArrayList;

public class PlayerHand {

    private List<Card> playerCards;

    public PlayerHand(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }
}

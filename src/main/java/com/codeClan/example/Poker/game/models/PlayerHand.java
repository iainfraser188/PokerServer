package com.codeClan.example.Poker.game.models;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class PlayerHand {

    private ArrayList<Card> playerCards;

    public PlayerHand(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }
}

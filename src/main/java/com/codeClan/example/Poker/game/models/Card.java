package com.codeClan.example.Poker.game.models;

public class Card {

    private CardSuitType cardSuitType;
    private CardFace cardFace;

    public Card(CardSuitType cardSuitType, CardFace cardFace) {
        this.cardSuitType = cardSuitType;
        this.cardFace = cardFace;
    }

    public CardSuitType getCardSuitType() {
        return cardSuitType;
    }

    public void setCardSuitType(CardSuitType cardSuitType) {
        this.cardSuitType = cardSuitType;
    }

    public CardFace getCardValue() {
        return cardFace;
    }

    public void setCardValue(CardFace cardFace) {
        this.cardFace = cardFace;
    }
}

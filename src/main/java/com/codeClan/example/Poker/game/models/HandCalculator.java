package com.codeClan.example.Poker.game.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HandCalculator {

    // CALCULATES SETS

    public int isFourOfAKind(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int isFour = 0;
        for (int i = 0; i < cards.size() - 3; i++ ) {

            // Requires getIntValue() after initial get() function.

            if(cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()
                    && cards.get(i+1).getCardValue().getValue()  == cards.get(i+2).getCardValue().getValue()
                    && cards.get(i+2).getCardValue().getValue()  == cards.get(i+3).getCardValue().getValue() ) {
                isFour = cards.get(i).getCardValue().getValue() ;
            }
        }
        if(isFour == 1){
            isFour = 14;
        }
        return isFour;
    }

    public int isThreeOfAKind(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int value = 0;
        boolean aces = false;

        // Requires getIntValue() after initial get() function.
        for (int i = 0; i < cards.size() - 2; i++ ) {
            if( cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()
                    && cards.get(i+1).getCardValue().getValue() == cards.get(i+2).getCardValue().getValue()) {
                value = cards.get(i).getCardValue().getValue();
                if (value == 1){
                    aces = true;
                }
            }
        }
        if (aces){
            value = 14;
        }
        return value;
    }

    public int isPair(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int value = 0;
        boolean aces = false;

        // Requires getIntValue() after initial get() function.
        for (int i = 0; i < cards.size() - 1; i++ ) {
            if(cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()) {
                value = cards.get(i).getCardValue().getValue();
                if (value == 1){
                    aces = true;
                }
            }
        }

        if (aces){
            value = 14;
        }
        return value;
    }

    // CALCULATES FLUSH

    public int isFlush(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        int value = 0;
        boolean ace = false;
        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> clubs = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {

            if (cards.get(i).getCardSuitType() == CardSuitType.HEARTS){
                hearts.add(cards.get(i));
            } else if (cards.get(i).getCardSuitType() == CardSuitType.DIAMONDS){
                diamonds.add(cards.get(i));
            } else if (cards.get(i).getCardSuitType() == CardSuitType.CLUBS){
                clubs.add(cards.get(i));
            } else if (cards.get(i).getCardSuitType() == CardSuitType.SPADES) {
                spades.add(cards.get(i));
            }
        }

        // Needs getIntValue added properly and sort function set up
        if (hearts.size() >= 5){
            Collections.sort(hearts, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = hearts.size();
            int highest = hearts.get(size-1).getCardValue().getValue();
            value = highest;
            if(hearts.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        } else if (diamonds.size() >= 5){
            Collections.sort(diamonds, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = diamonds.size();
            int highest = diamonds.get(size-1).getCardValue().getValue();
            value = highest;
            if(diamonds.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        } else if (clubs.size() >= 5){
            Collections.sort(clubs, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = clubs.size();
            int highest = clubs.get(size-1).getCardValue().getValue();
            value = highest;
            if(clubs.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        } else if (spades.size() >= 5) {
            Collections.sort(spades, Comparator.comparingInt(card -> card.getCardValue().getValue()));
            int size = spades.size();
            int highest = spades.get(size-1).getCardValue().getValue();
            value = highest;
            if(spades.get(0).getCardValue().getValue() == 1){
                value = 14;
            }
        }
        return value;
        // Edit this to return hashmap including value and arraylist of flush cards for use in straight flush calculator
    }

    public int isStraight(PlayerHand playerHand) {
        List<Card> cards = playerHand.getPlayerCards();
        Collections.sort(cards, Comparator.comparingInt(card -> card.getCardValue().getValue()));
        int consecutive = 1;
        int straight = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            if(cards.get(i).getCardValue().getValue() + 1 == cards.get(i+1).getCardValue().getValue()) {
                consecutive++;
                if (consecutive > 4) {
                    straight = cards.get(i+1).getCardValue().getValue();
                }
            } else if (cards.get(i).getCardValue().getValue() == cards.get(i+1).getCardValue().getValue()) {
                //nothing required
            } else {
                consecutive = 1;
            }
        }

        if(consecutive >= 4 && cards.get(0).getCardValue().getValue() == 1 && cards.get(6).getCardValue().getValue() == 13) {
            straight = 14;
        }

        return straight;
    }

    public int isStraightFlush(PlayerHand playerHand) {
        int value = 0;
        // Use flush calculator and then straight calculator;
        return value;
    }

//    DOESNT WORK AS PAIR ALWAYS RETURNS HIGHEST PAIR, MAY CLASH WITH TRIPS
//    public int isFullHouse(PlayerHand playerHand) {
//        int pair = this.isPair(playerHand);
//        int trips = this.isThreeOfAKind(playerHand);
//        if(pair != 0 && trips != 0 && pair != trips) {
//            if(trips == 1) {
//                trips = 14;
//            }
//            return trips;
//        } else {
//            return 0;
//        }
//    }


}

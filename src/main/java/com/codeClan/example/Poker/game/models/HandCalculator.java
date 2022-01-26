package com.codeClan.example.Poker.game.models;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HandCalculator {

    // CALCULATES SETS

    public int isFourOfAKind(PlayerHand playerHand) {
        Collections.sort(playerHand);
        int isFour = 0;
        for (int i = 0; i < playerHand.size() - 3; i++ ) {

            // Requires getIntValue() after initial get() function.

            if(playerHand.get(i) == playerHand.get(i+1)
                    && playerHand.get(i+1) == playerHand.get(i+2)
                    && playerHand.get(i+2) == playerHand.get(i+3)) {
                isFour = playerHand.get(i).getIntValue();
            }
        }
        if(isFour == 1){
            isFour = 14;
        }
        return isFour;
    }

    public int isThreeOfAKind(PlayerHand playerHand) {
        Collections.sort(playerHand);
        int value = 0;
        boolean aces = false;

        // Requires getIntValue() after initial get() function.
        for (int i = 0; i < playerHand.size() - 2; i++ ) {
            if( playerHand.get(i) == playerHand.get(i+1)
                    && playerHand.get(i+1) == playerHand.get(i+2)) {
                value = playerHand.get(i);
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
        Collections.sort(playerHand);
        int value = 0;
        boolean aces = false;

        // Requires getIntValue() after initial get() function.
        for (int i = 0; i < playerHand.size() - 1; i++ ) {
            if(playerHand.get(i) == playerHand.get(i+1)) {
                value = playerHand.get(i).getIntValue();
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
        int value = 0;
        boolean ace = false;
        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> clubs = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();

        for (int i = 0; i < playerHand.size(); i++) {

        // Needs extra getter before equality

            if (playerHand.get(i) == hearts){
                hearts.add(playerHand.get(i));
            } else if (playerHand.get(i) == diamonds){
                diamonds.add(playerHand.get(i));
            } else if (playerHand.get(i) == clubs){
                clubs.add(playerHand.get(i));
            } else if (playerHand.get(i) == spades) {
                spades.add(playerHand.get(i));
            }
        }

        // Needs getIntValue added properly and sort function set up
        if (hearts.size() >= 4){
            hearts.sort(hearts.get(i).getIntValue()...)
            int size = hearts.size();
            int highest = hearts.get(size-1).getIntValue()...;
            value = highest;
            if(hearts.get(0).getIntValue() == 1){
                value = 14;
            }
        } else if (diamonds.size() >= 4){
            diamonds.sort(diamonds.get(i).getIntValue()...)
            int size = diamonds.size();
            int highest = diamonds.get(size-1).getIntValue()...;
            value = highest;
            if(diamonds.get(0).getIntValue() == 1){
                value = 14;
            }
        } else if (clubs.size() >= 4){
            clubs.sort(clubs.get(i).getIntValue()...)
            int size = clubs.size();
            int highest = clubs.get(size-1).getIntValue()...;
            value = highest;
            if(clubs.get(0).getIntValue() == 1){
                value = 14;
            }
        } else if (spades.size() >= 4) {
            spades.sort(spades.get(i).getIntValue()...)
            int size = spades.size();
            int highest = spades.get(size-1).getIntValue()...;
            value = highest;
            if(spades.get(0).getIntValue() == 1){
                value = 14;
            }
        }
        return value;
        // Edit this to return hashmap including value and arraylist of flush cards for use in straight flush calculator
    }

    public int isStraight(PlayerHand playerHand) {
        Collections.sort(playerHand);
        int consecutive = 1;
        int straight = 0;
        for (int i = 0; i < playerHand.size() - 4; i++) {
            if(playerHand.get(i).getIntValue() + 1 == playerHand.get(i+1).getIntValue) {
                consecutive++;
                if (consecutive > 4) {
                    straight = playerHand.get(i+1);
                }
            } else if (playerHand.get(i).getIntValue() == playerHand.get(i+1).getIntValue) {
                //nothing required
            } else {
                consecutive = 1;
            }
        }

        if(consecutive >= 4 && playerHand.get(0).getIntValue() == 1) {
            straight = 14;
        }

        return straight;
    }

    public int isStraightFlush(PlayerHand playerHand) {
        // Use flush calculator and then straight calculator;
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

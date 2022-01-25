package com.codeClan.example.Poker.game.models;

import java.util.List;

public class Player {

    private String name;
    private double stack;
    private List<Card> hand;


    public Player(String name, double stack, List<Card> hand) {
        this.name = name;
        this.stack = stack;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStack() {
        return stack;
    }

    public void setStack(double stack) {
        this.stack = stack;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public double addToStack(double amount){
        return  this.stack += amount;
    }

    public double removeFromStack(double amount){
        return this.stack -= amount;
    }

}

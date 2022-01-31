package com.codeClan.example.Poker.webSocket.models;

public class PlayerAction {

    private String action;
    private long playerId;
    private double betAmount;

    public PlayerAction(String action, long playerId, double betAmount) {
        this.action = action;
        this.playerId = playerId;
        this.betAmount = betAmount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
}

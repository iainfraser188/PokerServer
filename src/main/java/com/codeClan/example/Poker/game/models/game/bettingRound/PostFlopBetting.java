package com.codeClan.example.Poker.game.models.game.bettingRound;

import com.codeClan.example.Poker.game.models.Player;

import java.util.List;

public class PostFlopBetting {


    private List<Player> players;
    private double pot;
    private double largestContribution;
    private boolean complete;


    public PostFlopBetting(List<Player> players, double pot) {
        this.players = players;
        this.pot = pot;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public double getLargestContribution() {
        return largestContribution;
    }

    public void setLargestContribution(double largestContribution) {
        this.largestContribution = largestContribution;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

//    game mothods


}

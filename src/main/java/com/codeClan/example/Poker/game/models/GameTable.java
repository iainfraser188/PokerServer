package com.codeClan.example.Poker.game.models;

import java.util.List;

public class GameTable {

    private double pot;
    private List<Player> players;
    private List<Card> board;

    public GameTable(double pot, List<Player> players, List<Card>board) {
        this.pot = pot;
        this.players = players;
        this.board = board;
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getBoard() {
        return board;
    }

    public void setBoard(List<Card> board) {
        this.board = board;
    }

    public void addToPot(double amount) {
        this.pot += amount;
    }

    public void removeFromPot(double amount){
        this.pot -= amount;
    }

    public void resetPot(){
        this.pot = 0;
    }
}

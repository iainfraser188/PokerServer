package com.codeClan.example.Poker.game.models;

import com.codeClan.example.Poker.game.models.game.bettingRound.PreFlopBetting;

import java.util.List;

public class GameTable {

    private double pot;
    private List<Player> players;
    private List<Card> board;
    private double bigBlind;
    private double smallBlind;

    public GameTable(double pot, List<Player> players, List<Card>board, double bigBlind) {
        this.pot = pot;
        this.players = players;
        this.board = board;
        this.bigBlind = bigBlind;
        this.smallBlind = bigBlind/2;
    }

    public double getPot() {
        return pot;
    }

    public double getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(double bigBlind) {
        this.bigBlind = bigBlind;
    }

    public double getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(double smallBlind) {
        this.smallBlind = smallBlind;
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

    public void addCardToBoard(Card card) {
        this.board.add(card);
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


    // GAME METHODS

    // round 1: pre-flop
    public void preFlopBettingRound() {
        PreFlopBetting preFlopBetting = new PreFlopBetting(this.players, this.bigBlind);
        preFlopBetting.setUpRound();
        preFlopBetting.addBlindsToPot();
        preFlopBetting.postBlindBetting();
    }


}

package com.codeClan.example.Poker.game.models;

import com.codeClan.example.Poker.game.models.game.bettingRound.PreFlopBetting;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="game")
public class GameTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="pot")
    private double pot;

    @Column(name="players")
    private ArrayList<Player> players;

    @Column(name="board")
    private ArrayList<Card> board;

    @Column(name="bigBlind")
    private double bigBlind;

    @Column(name="smallBlind")
    private double smallBlind;

    public GameTable(double pot, ArrayList<Player> players, ArrayList<Card>board, double bigBlind) {
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

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<Card> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Card> board) {
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

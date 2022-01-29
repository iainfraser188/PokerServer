package com.codeClan.example.Poker.game.models.game.bettingRound;

import com.codeClan.example.Poker.game.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
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

//    game methods


    public void setFirstPlayerToActive() {
        int smallBlindIndex = 0;
        for (Player player: this.players) {
            if (player.isSmallBlind()) {
                smallBlindIndex = this.players.indexOf(player);
            }
        }
        players.get(smallBlindIndex).setActive(true);
    }

    public void setUpRound() {
        // Set first player

        for (Player player : this.players) {
            player.setActive(false);
        }
        setFirstPlayerToActive();
    }


    public void Betting() {



//        boolean equalPotContribution = false;
//        boolean blindHasBet = false;
//
//        while (!equalPotContribution) {
//
//            equalPotContribution = true;
//
//            double largestContribution = this.findLargestContribution();
//            int counter = 0;
//
//            for (Player player : this.players) {
////                if (player.getContribution() != this.largestContribution && (player != bigBlindPlayer || blindHasBet)) {
////                if (player.getContribution() != this.findLargestContribution() && !blindHasBet) {
//                if (player.getContribution() == largestContribution) {
////                    equalPotContribution = false;
//                    counter ++;
//                }
//            }
//            if (counter != this.players.size()) {
//                equalPotContribution = false;
//            } else {
//                equalPotContribution = true
//            }
//
//
//            if (!equalPotContribution) {
//                this.handleActivePlayerTurn();
//            } else {
//                this.setComplete(true);
//                break;
//            }
//
//        }

//        int loopCounter = 0; // test

        boolean continueLoop = true;

        while(continueLoop) {

//            System.out.println("While loop counter: " + loopCounter); // test
//            loopCounter ++; // test

            this.handleActivePlayerTurn(); // player action

            continueLoop = !this.checkContributionsAreSame();
//            continueLoop = false;
            System.out.println("continueLoop: " + continueLoop); // test
            System.out.println("p1 stack contribution: " + this.players.get(0).getStack() + this.players.get(0).getContribution());
            System.out.println("p2 stack contribution: " + this.players.get(1).getStack() + this.players.get(1).getContribution());
            System.out.println("p3 stack contribution: " + this.players.get(2).getStack() + this.players.get(2).getContribution());
        }

        System.out.println("Exited while checkContributionsAreSame");
        this.setComplete(true);

    }

    public void handleActivePlayerTurn() {
        Player activePlayer = new Player();
        int activePlayerIndex = 0;
        for (Player player: this.players) {
            if (player.isActive()) {
                activePlayer = player;
                activePlayerIndex = this.players.indexOf(player);
            }
        }

        // wait until active player changes his "active" attribute to false after his move
        while(activePlayer.isActive()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("Waiting for active player to make their move and assign themselves as inactive");
//            }
//            System.out.println("active player:" + activePlayer);
//            System.out.println("active player stack:" + activePlayer.getStack());
            activePlayer.call(this.getLargestContribution()); // test hard-code
        }

        // player will assign himself as not active after his action
        // assign next active player
        activePlayerIndex ++;
        if (activePlayerIndex == this.players.size()) {
            activePlayerIndex = 0;
        }
        this.players.get(activePlayerIndex).setActive(true);
        // return the player that just acted
//        return activePlayer;
    }


    //    find largest contribution
    public double findLargestContribution(){
        double newLargestContribution = 0;
        for (Player player: this.players){
            if (player.getContribution() > this.largestContribution){
                newLargestContribution = player.getContribution();
            } else {
                newLargestContribution = this.getLargestContribution();
            }
        }
        return newLargestContribution;
    }

    //    check players have equal contributions
    public boolean checkContributionsAreSame() {
        boolean playersAreSame = true;
        for (Player player: this.players) {
            if (player.getContribution() != this.findLargestContribution()){
                playersAreSame = false;
            }
        }
        return playersAreSame;
    }



}

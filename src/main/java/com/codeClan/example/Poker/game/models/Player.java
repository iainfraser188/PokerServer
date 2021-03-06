package com.codeClan.example.Poker.game.models;

import com.vaadin.flow.component.ClientCallable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="stack")
    private double stack;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private ArrayList<Card> hand;

    @Column
    private boolean isBigBlind;

    @Column
    private boolean isSmallBlind;

    @Column
    private boolean isActive;

    @Column
    private double contribution;

    @Column
    private boolean folded;

    public Player() {
    }

    public Player(String name, double stack, String username, String password) {
        this.name = name;
        this.stack = stack;
        this.username = username;
        this.password = password;
        this.isBigBlind = false;
        this.isSmallBlind = false;
        this.isActive = false;
        this.contribution = 0;
        this.folded = false;
        this.hand = new ArrayList<>();

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

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double addToStack(double amount){
        return  this.stack += amount;
    }

    public double removeFromStack(double amount){
        return this.stack -= amount;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public boolean isBigBlind() {
        return isBigBlind;
    }

    public void setBigBlind(boolean bigBlind) {
        isBigBlind = bigBlind;
    }

    public boolean isSmallBlind() {
        return isSmallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        isSmallBlind = smallBlind;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getFolded() {
        return this.folded;
    }

    public void setFolded(Boolean folded) {
        this.folded = folded;
    }

    public void increaseContribution(double amount){
        double contributionAmount = this.getContribution() + amount;
        setContribution(contributionAmount);
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public boolean checkStackEnough(double betSize) {
        if (this.stack >= betSize) {
            return  true;
        }
        return false;
    }

    @ClientCallable
    public void bet(double betSize) {
        if (this.checkStackEnough(betSize)) {
            this.removeFromStack(betSize);
            this.increaseContribution(betSize);
            this.setActive(false);
        } else {
            this.fold();
        }
    }

    @ClientCallable
    public void call(double largestContribution) {
        double amountToCall = largestContribution - this.getContribution();
        if (this.checkStackEnough(amountToCall)) {
            this.removeFromStack(amountToCall);
            this.increaseContribution(amountToCall);
            this.setActive(false);
        } else {
            this.fold();
        }
    }

    @ClientCallable
    public void fold() {
        System.out.println("Folded");
        this.setActive(false);
        this.setFolded(true);
    }


}

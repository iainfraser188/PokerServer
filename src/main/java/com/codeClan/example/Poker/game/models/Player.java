package com.codeClan.example.Poker.game.models;

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

    @Transient
    private List<Card> hand;

    public Player() {
    }

    public Player(String name, double stack, String username, String password) {
        this.name = name;
        this.stack = stack;
        this.username = username;
        this.password = password;
        this.hand = new ArrayList<Card>();

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

}

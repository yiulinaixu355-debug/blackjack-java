package com.fengjialin.blackjack.model;

public class Player {
    private final String name;
    private final Hand hand = new Hand();
    private  int chips;
    private int bet;

    public Player(String name,int chips) {
        if ( chips < 0 ) {
            throw new IllegalArgumentException("初始筹码不能为0");
        }

        this.name = name;
        this.chips = chips;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getChips() {
        return chips;
    }

    public int getBet() {
        return bet;
    }

    public void placeBet(int amount) {
        if ( amount <= 0 || amount > chips ) {
            throw new IllegalArgumentException("下注金额必须在 1 到 " + chips + " 之间");
        }

        chips -= amount;
        bet = amount;
    }

    public boolean canDouble() {
        return bet > 0 && chips >= bet;
    }

    public void doubleBet() {
        if ( !canDouble() ) {
            throw new IllegalArgumentException("筹码不足，无法加倍");
        }

        chips -= bet;
        bet *= 2;
    }

    public void winBet() {
        chips += bet * 2;
    }

    public void pushBet() {
        chips += bet;
    }

    public void clearBet() {
        bet = 0;
    }

    public void newRound() {
        hand.clear();
    }
}

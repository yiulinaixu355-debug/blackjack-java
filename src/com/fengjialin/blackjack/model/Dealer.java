package com.fengjialin.blackjack.model;

public class Dealer {
    private final Hand hand = new Hand();

    public Hand getHand() {
        return hand;
    }

    public void newRound() {
        hand.clear();
    }

    public boolean mustHit() {
        return hand.value() < 17;
    }
}

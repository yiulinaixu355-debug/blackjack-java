package com.fengjialin.blackjack.model;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit,Rank rank) {
        if (suit == null || rank == null) {
            throw new IllegalArgumentException("花色或点数不能为空");
        }
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public String getDisplay() {
        return rank.getSymbol() + suit.getSymbol();
    }
}

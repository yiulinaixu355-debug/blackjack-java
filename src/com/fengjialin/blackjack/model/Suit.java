package com.fengjialin.blackjack.model;

public enum Suit {
    SPADE("♠"), HEART("♥"), CLUB("♣"), DIAMOND("♦");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

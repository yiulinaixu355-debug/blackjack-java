package com.fengjialin.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final double CUT_RATIO = 0.5;
    private final List<Card> cards = new ArrayList<>();
    private Random random = new Random();
    private int next;

    public Deck(int deckCount) {
        if (deckCount < 1) {
            throw  new IllegalArgumentException("牌副数至少为1");
        }
        for (int i = 0; i < deckCount; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit,rank));
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards,random);
        next = 0;
    }

    public Card draw() {
        if (next >= cards.size()) {
            throw  new IllegalArgumentException("牌靴已空，无法继续发牌");
        }
        return cards.get(next++);
    }

    public int remaining() {
        return cards.size() - next;
    }

    public int total() {
        return cards.size();
    }

    public boolean needsReshuffle() {
        return remaining() < total() * CUT_RATIO;
    }
}

package com.fengjialin.blackjack.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if(card == null) {
            throw new IllegalArgumentException("牌不能为空");
        }
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int value() {
        int total = 0;
        int ace = 0;

        for (Card card : cards) {
            total += card.getRank().getBaseValue();
            if (card.isAce()) {
                ace++;
            }
        }

        while (total > 21 && ace>0) {
            total -= 10;
            ace--;
        }

        return total;
    }

    public boolean isBust() {
        return value()>21;
    }

    public int size() {
        return cards.size();
    }
}

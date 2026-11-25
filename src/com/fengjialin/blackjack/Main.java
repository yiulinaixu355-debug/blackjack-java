package com.fengjialin.blackjack;

import com.fengjialin.blackjack.game.BlackjackGame;
import com.fengjialin.blackjack.util.InputReader;
import com.fengjialin.blackjack.view.ConsoleView;

public class Main {
    private static final int DECKS = 4;
    private static final int INITIAL_CHIPS = 100;
    private static final int MIN_BET = 10;

    public static void main(String[] args) {
        InputReader input = new InputReader();
        ConsoleView view = new ConsoleView();
        BlackjackGame game = new BlackjackGame(DECKS, INITIAL_CHIPS, MIN_BET, input, view);

        try {
            game.play();
        } finally {
            input.close();
        }
    }
}

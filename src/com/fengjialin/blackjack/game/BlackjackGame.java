package com.fengjialin.blackjack.game;

import com.fengjialin.blackjack.model.Dealer;
import com.fengjialin.blackjack.model.Deck;
import com.fengjialin.blackjack.util.InputReader;
import com.fengjialin.blackjack.model.Player;
import com.fengjialin.blackjack.view.ConsoleView;

public class BlackjackGame {

    private static final int SAFE_CARDS_BEFORE_ROUND = 15;

    private final Deck deck;
    private final Player player;
    private final Dealer dealer = new Dealer();
    private final InputReader input;
    private final ConsoleView view;
    private final int deckCount;
    private final int minBet;
    private int round;

    public BlackjackGame(int deckCount, int initialChips, int minBet,
                         InputReader input, ConsoleView view) {
        if (minBet <= 0) {
            throw new IllegalArgumentException("最低下注必须大于 0");
        }
        this.deckCount = deckCount;
        this.deck = new Deck(deckCount);
        this.player = new Player("玩家", initialChips);
        this.minBet = minBet;
        this.input = input;
        this.view = view;
    }

    public void play() {
        view.showWelcome(deckCount, player.getChips(), minBet);

        while (true) {
            if (player.getChips() < minBet) {
                view.showGameOver(player.getChips());
                return;
            }

            ensureEnoughCards();

            int bet = input.readInt("请下注（" + minBet + " - " + player.getChips() + "）：",
                    minBet, player.getChips());
            player.placeBet(bet);
            round++;

            startRound();
            playerTurn();

            if (!player.getHand().isBust()) {
                dealerTurn();
            } else {
                view.showMessage("庄家揭开暗牌：");
                view.render(dealer, player, false);
            }

            settle();
            player.clearBet();

            if (player.getChips() < minBet) {
                view.showGameOver(player.getChips());
                return;
            }

            if (!input.readYesNo("再来一局？")) {
                view.showGoodbye(player.getChips());
                return;
            }

            if (deck.needsReshuffle()) {
                deck.shuffle();
                view.showReshuffle();
            }
        }
    }

    private void ensureEnoughCards() {
        if (deck.remaining() < SAFE_CARDS_BEFORE_ROUND) {
            deck.shuffle();
            view.showReshuffle();
        }
    }

    private void startRound() {
        player.newRound();
        dealer.newRound();
        view.showRoundHeader(round);

        player.getHand().add(deck.draw());
        dealer.getHand().add(deck.draw());
        player.getHand().add(deck.draw());
        dealer.getHand().add(deck.draw());
    }

    private void playerTurn() {
        boolean firstAction = true;

        while (true) {
            view.render(dealer, player, true);

            boolean canDouble = firstAction && player.canDouble();
            String[] actions = canDouble
                    ? new String[]{"H", "S", "D"}
                    : new String[]{"H", "S"};
            String prompt = canDouble
                    ? "请选择（H 要牌 / S 停牌 / D 加倍）："
                    : "请选择（H 要牌 / S 停牌）：";

            String command = input.readCommand(prompt, actions);

            if ("S".equals(command)) {
                return;
            }

            if ("D".equals(command)) {
                player.doubleBet();
                player.getHand().add(deck.draw());
                view.showMessage("你选择了加倍，本局下注变为 " + player.getBet() + "。");
                view.render(dealer, player, true);

                if (player.getHand().isBust()) {
                    view.showMessage("你爆牌了，共 " + player.getHand().value() + " 点。");
                }
                return;
            }

            player.getHand().add(deck.draw());
            firstAction = false;

            if (player.getHand().isBust()) {
                view.render(dealer, player, true);
                view.showMessage("你爆牌了，共 " + player.getHand().value() + " 点。");
                return;
            }

            if (player.getHand().value() == 21) {
                view.render(dealer, player, true);
                view.showMessage("21 点，自动停牌。");
                return;
            }
        }
    }

    private void dealerTurn() {
        view.showMessage("庄家翻开暗牌：");
        view.render(dealer, player, false);

        while (dealer.mustHit()) {
            dealer.getHand().add(deck.draw());
            view.showMessage("庄家点数不足 17，继续要牌……");
            view.render(dealer, player, false);
        }

        if (dealer.getHand().isBust()) {
            view.showMessage("庄家爆牌，共 " + dealer.getHand().value() + " 点。");
        }
    }

    private void settle() {
        int playerValue = player.getHand().value();
        int dealerValue = dealer.getHand().value();
        GameResult result = judge(playerValue, dealerValue);

        switch (result) {
            case PLAYER_WIN -> player.winBet();
            case PUSH -> player.pushBet();
            case DEALER_WIN -> player.loseBet();
        }

        view.showResult(result.getDescription(), playerValue, dealerValue, player.getChips());
    }

    private GameResult judge(int playerValue, int dealerValue) {
        if (playerValue > 21) {
            return GameResult.DEALER_WIN;
        }
        if (dealerValue > 21) {
            return GameResult.PLAYER_WIN;
        }
        if (playerValue > dealerValue) {
            return GameResult.PLAYER_WIN;
        }
        if (playerValue < dealerValue) {
            return GameResult.DEALER_WIN;
        }
        return GameResult.PUSH;
    }
}

package com.fengjialin.blackjack.view;

import com.fengjialin.blackjack.model.Card;
import com.fengjialin.blackjack.model.Dealer;
import com.fengjialin.blackjack.model.Player;

import java.util.List;

public class ConsoleView {

    public void showWelcome(int deckCount, int chips, int minBet) {
        System.out.println("======================================");
        System.out.println("        21 点（Blackjack）控制台游戏");
        System.out.println("======================================");
        System.out.println("牌靴：" + deckCount + " 副牌    初始筹码：" + chips + "    最低下注：" + minBet);
        System.out.println("规则：A 可算 11 或 1；玩家爆牌判负；庄家 17 点以下必须继续要牌。");
        System.out.println("指令：H 要牌，S 停牌，D 加倍（仅首轮可用）。");
        System.out.println();
    }

    public void showRoundHeader(int round) {
        System.out.println("---------- 第 " + round + " 局 ----------");
    }

    public void render(Dealer dealer, Player player, boolean hideHole) {
        System.out.println("庄家：" + dealerCards(dealer, hideHole));
        System.out.println("玩家：" + cards(player.getHand().getCards())
                + "  共 " + player.getHand().value() + " 点");
        System.out.println("筹码：" + player.getChips() + "    本局下注：" + player.getBet());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showResult(String description, int playerValue, int dealerValue, int chips) {
        System.out.println();
        System.out.println("本局结果：" + description);
        System.out.println("玩家 " + playerValue + " 点，庄家 " + dealerValue + " 点，剩余筹码 " + chips + "。");
        System.out.println();
    }

    public void showReshuffle() {
        System.out.println(">>> 发牌已过半，切牌点到达，重新洗牌。");
    }

    public void showGameOver(int chips) {
        System.out.println();
        System.out.println("筹码只剩 " + chips + "，低于最低下注，游戏结束。");
    }

    public void showGoodbye(int chips) {
        System.out.println("你带着 " + chips + " 筹码离开了牌桌。");
    }

    private String dealerCards(Dealer dealer, boolean hideHole) {
        List<Card> hand = dealer.getHand().getCards();
        if (hand.isEmpty()) {
            return "（无）";
        }
        if (hideHole) {
            return format(hand.get(0)) + "  ??";
        }
        return cards(hand) + "  共 " + dealer.getHand().value() + " 点";
    }

    private String cards(List<Card> hand) {
        StringBuilder builder = new StringBuilder();
        for (Card card : hand) {
            builder.append(format(card));
        }
        return builder.toString();
    }

    private String format(Card card) {
        return String.format("%-4s", card.getDisplay());
    }
}
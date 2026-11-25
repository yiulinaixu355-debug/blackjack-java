package com.fengjialin.blackjack.game;

public enum GameResult {
    PLAYER_WIN("你赢了，押注翻倍返还"),
    DEALER_WIN("庄家赢了，押注归庄家"),
    PUSH("点数相同，平局，押注退回");

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

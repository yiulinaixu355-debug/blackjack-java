# blackjack-java

21 点（Blackjack）控制台游戏，Java 期末实践项目。

## 项目简介

控制台人机对战 21 点：玩家与庄家各持两手牌比点数，玩家可以要牌、停牌或加倍，庄家按固定规则行动，筹码输光游戏结束。

## 已实现功能

- 4 副牌混洗的牌靴，发牌过半自动切牌重洗
- A 的动态点数计算（先算 11，超过 21 自动降为 1，支持多张 A）
- 玩家要牌（Hit）、停牌（Stand）、加倍（Double Down）
- 庄家规则：小于 17 必须继续要牌，大于等于 17 停牌
- 下注、结算与筹码管理（胜、负、平局 Push）

## 目录结构

```text
src/com/fengjialin/blackjack/
├── Main.java                 程序入口（开发中）
├── model/                    数据模型
│   ├── Suit.java             花色
│   ├── Rank.java             点数
│   ├── Card.java             一张牌
│   ├── Deck.java             牌靴（开发中）
│   ├── Hand.java             手牌与点数计算
│   ├── Player.java           玩家与筹码（开发中）
│   └── Dealer.java           庄家规则（开发中）
├── game/                     游戏流程（开发中）
├── view/                     控制台渲染（开发中）
└── util/                     控制台输入（开发中）
```

## 编译与运行

```bat
javac -encoding UTF-8 -d out src\com\fengjialin\blackjack\**\*.java
java -cp out com.fengjialin.blackjack.Main
```

入口 `Main` 尚未完成，当前版本主要完成模型层，可直接编译。

## 开发进度

- [x] 花色、点数、卡牌模型
- [x] 手牌点数计算（含 A 的动态降级）
- [ ] 牌靴洗牌与发牌
- [ ] 玩家筹码与结算
- [ ] 回合流程与庄家规则
- [ ] 控制台界面与输入健壮性
- [ ] 加倍与切牌重洗

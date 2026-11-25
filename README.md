# blackjack-java

21 点（Blackjack）控制台游戏，Java 招新阶段性考核项目（选项五：控制台人机对战 21 点）。

## 项目简介

玩家与庄家各持一手牌比点数，玩家可以要牌、停牌或加倍，庄家按固定规则行动，筹码输光游戏结束。

## 功能特性

- **牌靴**：4 副牌混洗，发牌过半自动切牌重洗
- **点数**：A 先算 11，超过 21 自动降为 1，支持一手多张 A
- **玩家操作**：要牌（Hit）、停牌（Stand）、加倍（Double Down）
- **庄家规则**：小于 17 必须继续要牌，大于等于 17 停牌
- **筹码**：下注、胜负平结算，筹码不足自动结束
- **健壮性**：字母、空输入、越界数字都会提示并重新输入，不会崩溃

## 目录结构

```text
src/com/fengjialin/blackjack/
├── Main.java                 程序入口，只负责组装对象
├── model/                    数据模型
│   ├── Suit.java             花色枚举
│   ├── Rank.java             点数枚举（含基础点数）
│   ├── Card.java             一张牌
│   ├── Deck.java             牌靴：洗牌、发牌、切牌判断
│   ├── Hand.java             手牌与点数计算
│   ├── Player.java           玩家：筹码、下注、手牌
│   └── Dealer.java           庄家：手牌与停牌规则
├── game/
│   ├── GameResult.java       胜负平枚举
│   └── BlackjackGame.java    一局的完整流程
├── view/
│   └── ConsoleView.java      控制台渲染与提示
└── util/
    └── InputReader.java      带校验的输入读取
```

## 编译与运行

命令行（JDK 21）：

```bat
javac -encoding UTF-8 -d out src\com\fengjialin\blackjack\*.java src\com\fengjialin\blackjack\*\*.java
java -cp out com.fengjialin.blackjack.Main
```

也可以直接用 IDEA 打开项目，运行 `Main`。

## 操作说明

| 输入 | 含义 |
| --- | --- |
| 数字 | 下注金额，范围是最低下注到当前筹码 |
| `H` | 要牌 |
| `S` | 停牌 |
| `D` | 加倍（仅首轮可用，下注翻倍并只补一张牌） |
| `Y` / `N` | 是否再来一局 |

## 设计说明

- **分层**：`model` 只管数据与规则，`game` 负责流程编排，`view` 只做显示，`util` 处理输入，`Main` 只做组装。
- **下注即扣**：下注时从筹码里扣除，结算时只负责返还，避免重复计算。
- **A 的动态计算**：先把所有 A 按 11 累加，超过 21 时逐张减 10，直到不爆或没有 A 可降。
- **庄家规则集中**：`Dealer.mustHit()` 一个方法表达停牌线，以后调整规则只改这一处。

## 开发进度

- [x] 花色、点数、卡牌模型
- [x] 手牌点数计算（含 A 的动态降级）
- [x] 牌靴洗牌与发牌
- [x] 玩家筹码与结算
- [x] 回合流程与庄家规则
- [x] 控制台界面与输入健壮性
- [x] 加倍与切牌重洗

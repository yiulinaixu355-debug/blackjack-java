package com.fengjialin.blackjack.util;

import java.util.Arrays;
import java.util.Scanner;

public class InputReader {
    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt,int min,int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            if ( line.isEmpty() ) {
                System.out.println("输入不能为空，请重新输入。");
                continue;
            }

            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.println("请输入 " + min + " 到 " + max + " 之间的整数。");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("“" + line + "”不是有效的整数，请重新输入。");
            }
        }
    }

    public String readCommand(String prompt,String[] allowed) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().toUpperCase();

            if ( line.isEmpty() ) {
                System.out.println("输入不能为空，请重新输入。");
                continue;
            }

            for (String command : allowed) {
                if( command.equals(line) ) {
                    return line;
                }
            }
            System.out.println("无效的指令，请输入 " + Arrays.toString(allowed) + "。");
        }
    }

    public boolean readYesNo
            (String prompt) {
        return "Y".equals(readCommand(prompt + "（Y/N）：", new String[]{"Y","N"}));
    }

    public void close() {
        scanner.close();
    }
}

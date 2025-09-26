package com.youqusoft.vision.flow.generator;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class BannerGenerator implements Banner {
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        String appName = "YouQuSoft"; // 可以从配置中读取
        printCustomBanner(out, appName);
    }

    private void printCustomBanner(PrintStream out, String text) {
        out.println("\033[94m"); // ANSI蓝色

        // 生成简单的ASCII艺术字效果
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            printCharLine(out, c, i);
        }

        out.println("\033[0m"); // 重置颜色
    }

    private void printCharLine(PrintStream out, char c, int position) {
        // 根据字符生成对应的ASCII行
        switch (Character.toUpperCase(c)) {
            case 'Y':
                out.println("  ██    ██ ");
                out.println("   ██  ██  ");
                out.println("    ████   ");
                out.println("     ██    ");
                out.println("     ██    ");
                break;
            case 'O':
                out.println("  ██████  ");
                out.println(" ██    ██ ");
                out.println(" ██    ██ ");
                out.println(" ██    ██ ");
                out.println("  ██████  ");
                break;
            // 可以为每个字符添加对应的ASCII图案
            default:
                out.println("   " + c + "   ");
                break;
        }
    }
}

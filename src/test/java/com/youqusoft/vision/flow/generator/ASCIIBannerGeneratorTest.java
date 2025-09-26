package com.youqusoft.vision.flow.generator;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ASCIIBannerGeneratorTest {

    // ASCII字符集，按密度排序，用于灰度到ASCII的映射
    private static final String ASCII_CHARS = "@%#*+=-:. ";

    /**
     * 使用字体渲染生成ASCII艺术字
     * @param text 要转换的文本
     * @param font 字体
     * @param width 图像宽度
     * @param height 图像高度
     * @return ASCII艺术字字符串
     */
    public String generateAsciiArt(String text, Font font, int width, int height) {
        // 创建一个图像用于测量文本
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        
        // 计算文本边界
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, frc);
        
        g2d.dispose();
        
        // 创建适当大小的图像
        int imgWidth = (int) Math.ceil(bounds.getWidth());
        int imgHeight = (int) Math.ceil(bounds.getHeight());
        img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imgWidth, imgHeight);
        g2d.setColor(Color.BLACK);
        
        // 绘制文本
        g2d.drawString(text, 0, (int) -bounds.getY());
        g2d.dispose();
        
        // 将图像转换为ASCII艺术
        return convertImageToAscii(img);
    }
    
    /**
     * 将图像转换为ASCII艺术
     * @param image 要转换的图像
     * @return ASCII艺术字符串
     */
    private String convertImageToAscii(BufferedImage image) {
        StringBuilder asciiArt = new StringBuilder();
        int width = image.getWidth();
        int height = image.getHeight();
        
        for (int y = 0; y < height; y += 2) { // 每两个像素一行，因为字符的高度通常大于宽度
            for (int x = 0; x < width; x++) {
                // 获取像素颜色并转换为灰度
                Color color = new Color(image.getRGB(x, y));
                int gray = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
                
                // 将灰度值映射到ASCII字符
                int index = (gray * (ASCII_CHARS.length() - 1)) / 255;
                asciiArt.append(ASCII_CHARS.charAt(index));
            }
            asciiArt.append("\n");
        }
        
        return asciiArt.toString();
    }

    /**
     * 生成简单的块状ASCII艺术字
     * @param text 要转换的文本
     * @return ASCII艺术字字符串
     */
    public String generateBlockAsciiArt(String text) {
        List<String[]> letters = new ArrayList<>();
        
        // 为每个字符创建图案
        for (char c : text.toCharArray()) {
            letters.add(createLetterPattern(Character.toUpperCase(c)));
        }
        
        // 组合所有字符的行
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < 5; row++) { // 5行高
            for (String[] letter : letters) {
                if (row < letter.length) {
                    result.append(letter[row]);
                } else {
                    result.append("     "); // 默认填充
                }
                result.append(" "); // 字符间距
            }
            result.append("\n");
        }
        
        return result.toString();
    }
    
    /**
     * 为单个字符创建图案
     * @param c 字符
     * @return 字符的图案数组
     */
    private String[] createLetterPattern(char c) {
        switch (c) {
            case 'Y':
                return new String[]{"██  ██", " ██ ██", "  ███ ", "   ██ ", "   ██ "};
            case 'O':
                return new String[]{" █████ ", "██   ██", "██   ██", "██   ██", " █████ "};
            case 'U':
                return new String[]{"        ", "██   ██", "██   ██", "██   ██", " █████ "};
            case 'Q':
                return new String[]{" █████  ", "██   ██ ", "██   ██ ", "██  ███ ", " ██████ "};
            case 'S':
                return new String[]{" ██████", "██     ", " █████ ", "     ██", " █████ "};
            case 'F':
                return new String[]{"██████ ", "██     ", "████   ", "██     ", "██     "};
            case 'T':
                return new String[]{"███████", "   ██   ", "   ██   ", "   ██   ", "   ██   "};
            case 'V':
                return new String[]{"██   ██", "██   ██", "██   ██", " ██ ██ ", "  ███  "};
            case 'I':
                return new String[]{"██████", "  ██  ", "  ██  ", "  ██  ", "██████"};
            case 's':
                return new String[]{" █████", "██    ", " ████ ", "    ██", " █████"};
            case 'N':
                return new String[]{"██   ██", "███  ██", "████ ██", "██ ████", "██   ██"};
            case 'W':
                return new String[]{"██   ██", "██   ██", "██ █ ██", "███████", " █ █ █ "};
            case 'A':
                return new String[]{"   ██   ", "  ████  ", " ██  ██ ", "████████", "██    ██"};
            case 'R':
                return new String[]{"██████ ", "██   ██", "██████ ", "██   ██", "██   ██"};
            case 'E':
                return new String[]{"███████", "██     ", "█████  ", "██     ", "███████"};
            case 'G':
                return new String[]{" █████ ", "██     ", "██  ███", "██   ██", " █████ "};
            case 'L':
                return new String[]{"██     ", "██     ", "██     ", "██     ", "███████"};
            case 'P':
                return new String[]{"██████ ", "██   ██", "██████ ", "██     ", "██     "};
            case 'H':
                return new String[]{"██   ██", "██   ██", "███████", "██   ██", "██   ██"};
            case 'B':
                return new String[]{"██████ ", "██   ██", "██████ ", "██   ██", "██████ "};
            case 'C':
                return new String[]{" █████ ", "██     ", "██     ", "██     ", " █████ "};
            case 'D':
                return new String[]{"██████ ", "██   ██", "██   ██", "██   ██", "██████ "};
            case 'M':
                return new String[]{"██    ██", "███  ███", "████████", "██ ██ ██", "██    ██"};
            case 'X':
                return new String[]{"██   ██", " ██ ██ ", "  ███  ", " ██ ██ ", "██   ██"};
            case 'Z':
                return new String[]{"███████", "   ██  ", "  ██   ", " ██    ", "███████"};
            default:
                return new String[]{"      ", "      ", "      ", "      ", "      "};
        }
    }

    @Test
    public void testGenerateYouQuSoftBannerWithFont() {
        System.out.println("使用字体渲染生成的ASCII艺术字:");
        String asciiArt = generateAsciiArt("YouQuSoft", new Font("Arial", Font.BOLD, 20), 200, 50);
        System.out.println(asciiArt);
    }
    
    @Test
    public void testGenerateYouQuSoftBannerWithBlocks() {
        System.out.println("使用块状图案生成的ASCII艺术字:");
        String asciiArt = generateBlockAsciiArt("YouQuSoft");
        System.out.println(asciiArt);
    }
    
    @Test
    public void testGenerateCustomTextBanner() {
        // 可以替换成任何您想要的文本
        String customText = "YouQuSoft";
        System.out.println("生成 " + customText + " ASCII 艺术字 (块状):");
        String asciiArt = generateBlockAsciiText(customText);
        System.out.println(asciiArt);
    }
    
    public String generateBlockAsciiText(String text) {
        return generateBlockAsciiArt(text);
    }
}
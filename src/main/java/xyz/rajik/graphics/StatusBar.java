package xyz.rajik.graphics;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class StatusBar extends DisplayObject {
    private static String scoreText = "Score: ";
    private static String bricksLeftText = "Bricks left: ";
    private int score;
    private int bricksLeft;

    public StatusBar(int x, int y, int width, int height) {
        score = 0;
        bricksLeft = 0;
        this.isCollisional = false;
        this.x = x;
        this.y = y;
        this.maxX = x + width;
        this.maxY = y + height;
        this.width = width;
        this.height = height;
    }
    @Override
    protected void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Sans", Font.PLAIN, 22));
        g.drawString(scoreText + score, (int) x, (int) y);
        g.drawString(bricksLeftText + bricksLeft, (int) x + 100, (int) y);
    }
}

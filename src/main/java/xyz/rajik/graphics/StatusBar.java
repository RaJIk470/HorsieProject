package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.Game;

import javax.swing.*;
import java.awt.*;

@Data
@NoArgsConstructor
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
        g.setFont(new Font("Sans", Font.PLAIN, 30));
        g.drawString(scoreText + score,  x,  y);
        g.drawString(bricksLeftText + bricksLeft,  x + 150,  y);
        g.drawString("Difficulty: " + Game.game.getMenu().getDifficulty().getSelectedItem(),  x + 370,  y);
        g.drawString("Res: " + Game.game.getGameField().getMenu().getScreenSize().getSelectedItem(),  x + 650,  y);
        g.drawString("Name: " + Game.game.playerName,  x + 900,  y);
    }

    public void addScore(int score) {
        this.score += score;
    }
}

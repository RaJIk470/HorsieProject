package xyz.rajik;

import lombok.Data;

import javax.swing.*;

@Data
public class StatusBar extends JPanel {
    private static String scoreText = "Score: ";
    private static String bricksLeftText = "Bricks left: ";
    private int score;
    private int bricksLeft;
    private JLabel scoreLabel;
    private JLabel bricksLeftLabel;

    public StatusBar() {
        score = 0;
        bricksLeft = 0;
        scoreLabel = new JLabel(scoreText + scoreText);
        bricksLeftLabel = new JLabel(bricksLeftText + bricksLeft);
    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText(scoreText + score);
    }

    public void updateBricks(int bricksLeft) {
        this.bricksLeft = bricksLeft;
        bricksLeftLabel.setText(bricksLeftText + bricksLeft);
    }
}

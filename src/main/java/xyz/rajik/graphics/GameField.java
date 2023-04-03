package xyz.rajik.graphics;

import lombok.Data;
import xyz.rajik.collections.Balls;

import javax.swing.*;
import java.awt.*;

@Data
public class GameField extends JPanel {
    private Balls balls;
    public GameField() {
        balls = new Balls();
        balls.getBalls().add(new Ball(200, 10, 50, 50, 5, Color.BLACK));
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Ball ball : balls) ball.draw((Graphics2D)g);
    }
}

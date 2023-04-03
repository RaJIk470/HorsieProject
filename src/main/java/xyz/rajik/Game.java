package xyz.rajik;

import lombok.Data;
import xyz.rajik.collections.Balls;
import xyz.rajik.graphics.Ball;
import xyz.rajik.graphics.GameField;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Data
public class Game extends JFrame {
    public static int FPS = 60;
    public static int SECOND = (int) 1e9;
    public static int FIXED_LOOP_TIME = (int) (1e9 / FPS);
    private boolean isRunning;
    private long lastLoopTime;
    private int frameCount = 0;
    private double frameTime = 0;
    private GameField gameField;

    private int width;
    private int height;
    public Game(GameField gameField, int width, int height) {
        this.gameField = gameField;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setLayout(null);
        setVisible(true);
        setContentPane(gameField);
        isRunning = true;
    }

    private void fixedUpdate(double delta) {
        for (Ball ball : gameField.getBalls()) {
            if (ball.getLeftBound() <= 0)
                ball.getDirection().setRight();
            if (ball.getRightBound() >= width)
                ball.getDirection().setLeft();
            if (ball.getTopBound() <= 0)
                ball.getDirection().setDown();
            if (ball.getBottomBound() >= height)
                ball.getDirection().setUp();

            ball.move(delta);
        }
        gameField.repaint();
    }

    public void start() {
        lastLoopTime = System.nanoTime();
        loop();
    }

    public void loop() {
        double fixedLoopDelta = 0;
        while (isRunning) {
            double delta = getDelta();
            fixedLoopDelta += delta;
            frameTime += delta;
            if (fixedLoopDelta >= FIXED_LOOP_TIME) {
                frameCount++;
                fixedUpdate(fixedLoopDelta / SECOND);
                fixedLoopDelta = 0;
            }
            if (frameTime >= SECOND) {
                frameTime -= SECOND;
                frameCount = 0;
            }
        }
    }


    private void update(double delta) {

    }
    private double getDelta() {
        long now = System.nanoTime();
        long updateTime = now - lastLoopTime;
        lastLoopTime = now;

        double delta = updateTime;

        return delta;
    }
}

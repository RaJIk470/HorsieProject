package xyz.rajik;

import lombok.Data;
import xyz.rajik.collections.Balls;
import xyz.rajik.graphics.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Data
public class Game extends JFrame {
    public static int FPS = 60;
    public static int FRAME_TIME = 1000 / FPS;
    private boolean isRunning;
    private GameField gameField;

    private int width;
    private int height;
    private Timer timer;

    public Game(int width, int height) {
        this.gameField = new GameField(this, width, height);
        this.width = width;
        this.height = height;
        this.timer = new Timer(FRAME_TIME, (e) -> loop());
        setSize(width, height);
        setLayout(null);
        setVisible(true);
        setContentPane(gameField);
        isRunning = true;
    }

    private void fixedUpdate() {
        for (DisplayObject displayObject : gameField.getDisplayObjects()) {
            if (displayObject instanceof Moveable) {
                for (DisplayObject another : gameField.getDisplayObjects()) {
                    if (displayObject != another) {
                        if (displayObject.checkCollision(another)) {
                            displayObject.handleCollisionEvent(new CollisionEvent(another));
                            another.handleCollisionEvent(new CollisionEvent(displayObject));
                        }
                    }
                }
                Moveable moveable = (Moveable) displayObject;
                moveable.move(FRAME_TIME / (double)1000);
            }
        }
        gameField.repaint();
    }

    public void start() {
        timer.start();
    }

    public void loop() {
        fixedUpdate();
    }
}

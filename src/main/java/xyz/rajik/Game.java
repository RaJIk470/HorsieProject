package xyz.rajik;

import lombok.Data;
import xyz.rajik.collections.Balls;
import xyz.rajik.graphics.*;
import xyz.rajik.graphics.Menu;
import xyz.rajik.graphics.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        Menu menu = gameField.getMenu();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (isRunning) {
                        pauseGame();
                    } else {
                        resumeGame();
                    }
                }
            }
        });


        this.width = width;
        this.height = height;
        this.timer = new Timer(FRAME_TIME, (e) -> loop());
        setSize(width, height);
        setVisible(true);

        setLayout(null);
        setContentPane(gameField);

        isRunning = true;
    }

    private void fixedUpdate() {
        StatusBar statusBar = gameField.getStatusBar();
        int bricksLeft = (int) gameField.getBricks().getBricks().stream().filter((b) -> !b.isBroken()).count();
        statusBar.setScore((gameField.getBricks().getBricks().size() - bricksLeft) * 2);
        statusBar.setBricksLeft(bricksLeft);
        for (DisplayObject displayObject : gameField.getDisplayObjects()) {
            if (displayObject instanceof Moveable) {
                for (DisplayObject another : gameField.getDisplayObjects()) {
                    if (displayObject != another) {
                        if (another.isCollisional() && displayObject.checkCollision(another)) {
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

    public void pauseGame() {
        timer.stop();
        isRunning = false;
        gameField.getMenu().showMenu();
    }

    public void resumeGame() {
        timer.start();
        isRunning = true;
        gameField.getMenu().hideMenu();
    }
}

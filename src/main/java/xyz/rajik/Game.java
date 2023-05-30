package xyz.rajik;

import lombok.Data;
import xyz.rajik.collections.Balls;
import xyz.rajik.collections.Bonuses;
import xyz.rajik.graphics.*;
import xyz.rajik.graphics.Event;
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
    public int scoreMultiplier = 1;
    private boolean isRunning;
    private GameField gameField;
    public static Game game;
    public String playerName;

    private int width;
    private int height;
    private Timer timer;
    private Menu menu;

    public Game(int width, int height) {
        playerName = "Player";
        this.gameField = new GameField(this, width, height);
        menu = gameField.getMenu();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                menu.getNameField().setFocusable(false);
                menu.getNameField().setFocusable(true);
                requestFocus();
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
        setExtendedState(MAXIMIZED_BOTH);

        setLayout(null);
        setContentPane(gameField);

        isRunning = true;
        this.game = this;
    }

    private void fixedUpdate() {
        StatusBar statusBar = gameField.getStatusBar();
        int bricksLeft = (int) gameField.getBricks().getBricks().stream().filter((b) -> !b.isBroken()).count();
        Bonuses.update(FRAME_TIME / (double) 1000);
        //statusBar.setScore((gameField.getBricks().getBricks().size() - bricksLeft) * 2);
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
        gameField.getMenu().repaint();
        //setContentPane(menu);
    }

    public void resumeGame() {
        timer.start();
        isRunning = true;
        gameField.getMenu().hideMenu();
        //setContentPane(gameField);
    }

    public void gameOver() {
        Bonuses.unset();
        pauseGame();
        int bricksLeft = (int) gameField.getBricks().getBricks().stream().filter((b) -> !b.isBroken()).count();
        if (bricksLeft > 0) {
            GameAnnouncer.showMessage("You lost. Your score: " + gameField.getStatusBar().getScore());
        } else {
            GameAnnouncer.showMessage("You won. Your score: " + gameField.getStatusBar().getScore());
        }

        gameField.setupDefaultGameField();
    }

    public boolean checkIfGameOver() {
        int bricksLeft = (int) gameField.getBricks().getBricks().stream().filter((b) -> !b.isBroken()).count();
        return bricksLeft == 0;
    }

    public void setScreenSize(int width, int height) {
        setSize(width, height);
        gameField.setSize(width, height);
    }

    public void handleEvent(Event e) {
        e.execute(this);
    }
}

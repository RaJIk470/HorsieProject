package xyz.rajik;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.graphics.Paddle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

@Data
@NoArgsConstructor
public class Player extends KeyAdapter {
    private Paddle paddle;

    public Player(Paddle paddle) {
        this.paddle = paddle;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> paddle.getDirection().setLeft();
            case KeyEvent.VK_RIGHT -> paddle.getDirection().setRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_LEFT == e.getKeyCode() && paddle.getDirection().getX() < 0) {
            paddle.getDirection().reset();
        }
        if (KeyEvent.VK_RIGHT == e.getKeyCode() && paddle.getDirection().getX() > 0) {
            paddle.getDirection().reset();
        }
    }

    public void move(double dt) {
        paddle.move(dt);
    }
}

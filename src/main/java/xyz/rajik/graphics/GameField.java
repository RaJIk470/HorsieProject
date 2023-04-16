package xyz.rajik.graphics;

import lombok.Data;
import xyz.rajik.Game;
import xyz.rajik.Player;
import xyz.rajik.collections.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
public class GameField extends JPanel {
    private Balls balls;
    private Bricks bricks;
    private Players players;
    private DisplayObjects displayObjects;
    private Walls walls;
    private int width;
    private int height;
    private Game game;

    public GameField(Game game, int width, int height) {
        this.width = width;
        this.height = height;
        balls = new Balls();
        bricks = new Bricks();
        players = new Players();
        walls = new Walls();
        displayObjects = new DisplayObjects();
        addWalls();
        players.getPlayers().add(new Player(new Paddle(300, 700, 150, 50, 5, new GameColor(125, 125, 0))));
        for (Player player : players) {
            game.addKeyListener(player);
        }
        walls = new Walls();
        balls.getBalls().add(new Ball(200, 400, 50, 50, 10, new GameColor(0, 0, 0)));
        bricks.generateBricks(60, 40, 110, 50, width, 30);
        displayObjects.getDisplayObjects().addAll(balls.getBalls());
        displayObjects.getDisplayObjects().addAll(bricks.getBricks());
        displayObjects.getDisplayObjects().addAll(players.getPlayers().stream().map((p) -> p.getPaddle()).toList());

        setBackground(Color.WHITE);
    }

    public void addWalls() {
        /*walls.getWalls().addAll(List.of(new GameFieldWall(0, 0, width, 10),
                new GameFieldWall(0, 0, 10, height),
                new GameFieldWall(width - 10, 0, 10 , height),
                new GameFieldWall(0, height - 10, width, 10)));*/
        walls.getWalls().addAll(List.of(new GameFieldWall(0, 0, width, 4),
                new GameFieldWall(0, 0, 4, height),
                new GameFieldWall(width - 4, 0, 4 , height),
                new GameFieldWall(0, height - 4, width, 4)));

        displayObjects.getDisplayObjects().addAll(walls.getWalls());
    }




    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (DisplayObject displayObject : displayObjects) {
            displayObject.draw((Graphics2D) g);
        }
    }
}

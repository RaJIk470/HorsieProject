package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import xyz.rajik.*;
import xyz.rajik.collections.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import xyz.rajik.collections.DisplayObjects;

@Data
public class GameField extends JPanel {
    private Balls balls;
    private Bricks bricks;
    private Players players;
    private DisplayObjects displayObjects;
    private Walls walls;
    private int width;
    private int height;
    @JsonIgnore
    private transient Game game;
    private StatusBar statusBar;
    private Menu menu;

    public GameField(Game game,int width, int height) {
        this.game = game;
        menu = new Menu();
        this.add(menu);
        this.width = width;
        this.height = height;
        clearDisplayObjects();
        setupDefaultGameField();

        setBackground(Color.WHITE);
        menu.getResume().addActionListener((e -> game.resumeGame()));
        menu.getExit().addActionListener(e -> System.exit(0));
        menu.getSave().addActionListener(e -> {
            try {
                save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        menu.getLoad().addActionListener(e -> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void save() throws IOException {
        Serializer serializer = null;
        switch (menu.getType()) {
            case JSON -> serializer = new JsonFormatSerializer();
            case TEXT -> serializer = new TextFormatSerializer();
        }

        serializer.serialize(displayObjects);
    }

    public void load() throws IOException {
        Serializer serializer = null;
        switch (menu.getType()) {
            case JSON -> serializer = new JsonFormatSerializer();
            case TEXT -> serializer = new TextFormatSerializer();
        }

        setDisplayObjects(serializer.deserialize());
    }

    public void setupDefaultGameField() {
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

        statusBar = new StatusBar(40, 780, 760, 40);
        displayObjects.getDisplayObjects().add(statusBar);
    }

    public void clearDisplayObjects() {
        balls = new Balls();
        bricks = new Bricks();
        players = new Players();
        walls = new Walls();
        displayObjects = new DisplayObjects();
    }

    public void setDisplayObjects(DisplayObjects displayObjects) {
        clearDisplayObjects();
        for (DisplayObject d : displayObjects) {
            if (d instanceof Ball)
                balls.getBalls().add((Ball) d);
            if (d instanceof Brick)
                bricks.getBricks().add((Brick) d);
            if (d instanceof GameFieldWall)
                walls.getWalls().add((GameFieldWall) d);
            if (d instanceof Paddle) {
                Player newPlayer = new Player((Paddle) d);
                players.getPlayers().add(newPlayer);
                game.addKeyListener(newPlayer);
            }
            this.displayObjects.getDisplayObjects().add(d);
        }
        statusBar = new StatusBar(40, 760, 760, 40);
        this.displayObjects.getDisplayObjects().add(statusBar);
    }

    public void addWalls() {
        walls.getWalls().addAll(List.of(new GameFieldWall(0, 0, width, 8),
                new GameFieldWall(0, 0, 8, height),
                new GameFieldWall(width - 8, 0, 8 , height),
                new GameFieldWall(0, height - 8, width, 8)));

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

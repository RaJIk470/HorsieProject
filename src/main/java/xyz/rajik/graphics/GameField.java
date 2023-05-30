package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import xyz.rajik.*;
import xyz.rajik.collections.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import xyz.rajik.collections.DisplayObjects;
import xyz.rajik.proxy.JsonFormatSerializer;
import xyz.rajik.proxy.TextFormatSerializer;

@Data
public class GameField extends JPanel {
    private int difficulty;
    private Balls balls;
    private Bricks bricks;
    private Players players;
    private Bonuses bonuses;
    private DisplayObjects displayObjects;
    private Walls walls;
    private int width;
    private int height;
    @JsonIgnore
    private transient Game game;
    private StatusBar statusBar;
    private Menu menu;

    public GameField(Game game,int width, int height) {
        difficulty = 10;
        this.game = game;
        menu = new Menu(this);
        //this.add(menu);
        this.width = width;
        this.height = height;
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
        changeScreenSize(width, height);
        return;
        /*clearDisplayObjects();
        addWalls(width, height, 16);
        players.getPlayers().add(new Player(new Paddle(width / 2, height - 100, 150, 50, 7, new GameColor(125, 125, 0))));
        for (Player player : players) {
            game.addKeyListener(player);
        }
        walls = new Walls();
        balls.getBalls().add(new Ball(1200, 1200, 50, 50, 10, new GameColor(0, 0, 0)));
        bricks.generateBricks(60, 40, 110, 50, width, 198);
        bonuses.setBonuses(bricks.getBricks().stream().flatMap(b -> b.getBonusList().stream()).toList());

        displayObjects.getDisplayObjects().addAll(balls.getBalls());
        displayObjects.getDisplayObjects().addAll(bricks.getBricks());
        displayObjects.getDisplayObjects().addAll(players.getPlayers().stream().map((p) -> p.getPaddle()).toList());
        displayObjects.getDisplayObjects().addAll(bonuses.getBonuses());

        statusBar = new StatusBar(40, 1570, 2520, 40);
        displayObjects.getDisplayObjects().add(statusBar);*/
    }

    public void changeScreenSize(int width, int height) {
        int brickCount = 130;
        //if (bricks != null)
            //brickCount = (int) bricks.getBricks().stream().filter(b -> !b.isBroken).count();
        clearDisplayObjects();
        this.width = width;
        this.height = height;
        Paddle paddle = null;
        Ball ball = null;
        StatusBar sb = null;
        Brick brick = null;
        switch (width) {
            case 2560 -> {
                paddle = new Paddle(width / 2, height - 100, 150, 50, 7, new GameColor(125, 125, 0));
                ball = new Ball(1200, 1200, 50, 50, difficulty, new GameColor(0, 0, 0));
                sb = new StatusBar(40, 40, 2520, 40);
                bricks.generateBricks(60, 80, 110, 50, width, brickCount);
                addWalls(width, height, 16);
            }
            case 1920 -> {
                paddle = new Paddle(width / 2, height - 80, 130, 45, 7, new GameColor(125, 125, 0));
                ball = new Ball(800, 700, 45, 45, difficulty, new GameColor(0, 0, 0));
                sb = new StatusBar(40, 30, 1920, 40);
                bricks.generateBricks(50, 80, 70, 40, width, brickCount);
                addWalls(width, height, 12);
            }
            case 1650 -> {
                paddle = new Paddle(width / 2, height - 70, 110, 40, 7, new GameColor(125, 125, 0));
                ball = new Ball(700, 600, 35, 35, difficulty, new GameColor(0, 0, 0));
                sb = new StatusBar(40, 30, 1650, 40);
                bricks.generateBricks(40, 80, 60, 30, width, brickCount);
                addWalls(width, height, 10);
            }
            case 1024 -> {
                paddle = new Paddle(width / 2, height - 60, 80, 30, 7, new GameColor(125, 125, 0));
                ball = new Ball(500, 300, 25, 25, difficulty, new GameColor(0, 0, 0));
                sb = new StatusBar(40, 30, 1024, 40);
                bricks.generateBricks(40, 60, 40, 20, width, brickCount);
                addWalls(width, height, 6);
            }
            case 800 -> {
                paddle = new Paddle(width / 2, height - 60, 70, 25, 5, new GameColor(125, 125, 0));
                ball = new Ball(400, 400, 20, 20, difficulty, new GameColor(0, 0, 0));
                sb = new StatusBar(40, 30, 800, 40);
                bricks.generateBricks(40, 60, 25, 15, width, brickCount);
                addWalls(width, height, 4);
            }
        }
        players.getPlayers().add(new Player(paddle));
        for (Player player : players) {
            game.addKeyListener(player);
        }
        walls = new Walls();
        balls.getBalls().add(ball);
        bonuses.setBonuses(bricks.getBricks().stream().flatMap(b -> b.getBonusList().stream()).toList());

        displayObjects.getDisplayObjects().addAll(balls.getBalls());
        displayObjects.getDisplayObjects().addAll(bricks.getBricks());
        displayObjects.getDisplayObjects().addAll(players.getPlayers().stream().map((p) -> p.getPaddle()).toList());
        displayObjects.getDisplayObjects().addAll(bonuses.getBonuses());

        statusBar = sb;
        displayObjects.getDisplayObjects().add(statusBar);
        Bonuses.unset();

    }

    public void clearDisplayObjects() {
        bonuses = new Bonuses();
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
            if (d instanceof Brick brick) {
                bricks.getBricks().add(brick);
                this.displayObjects.getDisplayObjects().addAll(brick.getBonusList());
            }
            //if (d instanceof Bonus)
                //bonuses.getBonuses().add((Bonus) d);
            if (d instanceof GameFieldWall)
                walls.getWalls().add((GameFieldWall) d);
            if (d instanceof StatusBar) {
                statusBar = (StatusBar) d;
            }
            if (d instanceof Paddle) {
                Player newPlayer = new Player((Paddle) d);
                players.getPlayers().add(newPlayer);
                game.addKeyListener(newPlayer);
            }
            if (!(d instanceof Bonus))
                this.displayObjects.getDisplayObjects().add(d);
        }
        //this.displayObjects.getDisplayObjects().add(statusBar);
    }

    public void addWalls(int width, int height, int size) {
        walls.getWalls().addAll(List.of(new GameFieldWall(0, -size, width, size, WallType.BASIC_WALL),
                new GameFieldWall(-size, 0, size, height, WallType.BASIC_WALL),
                new GameFieldWall(width, 0, size, height, WallType.BASIC_WALL),
                new GameFieldWall(0, height, width, size, WallType.DEATH_WALL)));

        displayObjects.getDisplayObjects().addAll(walls.getWalls());
    }




    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (DisplayObject displayObject : displayObjects) {
            displayObject.draw((Graphics2D) g);
        }
    }

    public void setDifficulty(int d) {
        this.difficulty = d;
        balls.getBalls().stream().forEach(ball -> ball.setSpeed(d));
    }

}

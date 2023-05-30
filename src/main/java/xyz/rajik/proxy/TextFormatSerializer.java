package xyz.rajik.proxy;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import xyz.rajik.GameAnnouncer;
import xyz.rajik.Player;
import xyz.rajik.Serializer;
import xyz.rajik.collections.*;
import xyz.rajik.graphics.*;

import java.io.File;
import java.io.IOException;
import xyz.rajik.collections.DisplayObjects;
import xyz.rajik.proxy.SerializationData;

public class TextFormatSerializer implements Serializer {
    private YAMLMapper objectMapper;
    public TextFormatSerializer() {
        objectMapper = new YAMLMapper();
    }
    @Override
    public void serialize(DisplayObjects displayObjects) throws IOException {
        Balls balls = new Balls();
        Players players = new Players();
        Walls walls = new Walls();
        Bricks bricks  = new Bricks();
        Bonuses bonuses = new Bonuses();
        StatusBar statusBar = null;

        for (DisplayObject d : displayObjects) {
            if (d instanceof Ball) balls.getBalls().add((Ball) d);
            if (d instanceof Brick) bricks.getBricks().add((Brick) d);
            if (d instanceof Bonus) bonuses.getBonuses().add((Bonus) d);
            if (d instanceof GameFieldWall) walls.getWalls().add((GameFieldWall) d);
            if (d instanceof Paddle) players.getPlayers().add(new Player((Paddle) d));
            if (d instanceof StatusBar s) statusBar = s;
        }

        SerializationData save = new SerializationData(balls, players, walls, bricks, bonuses, statusBar);

        objectMapper.writeValue(new File("save.txt"), save);
    }

    @Override
    public DisplayObjects deserialize() {

        DisplayObjects displayObjects = new DisplayObjects();
        try {
            SerializationData save = objectMapper.readValue(new File("save.txt"), SerializationData.class);
            displayObjects.getDisplayObjects().addAll(save.getBalls().getBalls());
            displayObjects.getDisplayObjects().addAll(save.getPlayers().getPlayers().stream().map((p) -> p.getPaddle()).toList());
            displayObjects.getDisplayObjects().addAll(save.getWalls().getWalls());
            displayObjects.getDisplayObjects().addAll(save.getBonuses().getBonuses());
            displayObjects.getDisplayObjects().addAll(save.getBricks().getBricks());
            displayObjects.getDisplayObjects().add(save.getStatusBar());
        } catch (IOException e) {
            GameAnnouncer.showMessage("File doesn't exist or save is corrupted");
            throw new RuntimeException("Parsing error");
        }




        return displayObjects;
    }
}

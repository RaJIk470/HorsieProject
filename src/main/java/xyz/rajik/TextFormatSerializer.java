package xyz.rajik;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import xyz.rajik.collections.*;
import xyz.rajik.graphics.*;

import java.io.File;
import java.io.IOException;
import xyz.rajik.collections.DisplayObjects;

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

        for (DisplayObject d : displayObjects) {
            if (d instanceof Ball) balls.getBalls().add((Ball) d);
            if (d instanceof Brick) bricks.getBricks().add((Brick) d);
            if (d instanceof GameFieldWall) walls.getWalls().add((GameFieldWall) d);
            if (d instanceof Paddle) players.getPlayers().add(new Player((Paddle) d));
        }

        SerializationData save = new SerializationData(balls, players, walls, bricks);

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
            displayObjects.getDisplayObjects().addAll(save.getBricks().getBricks());
        } catch (IOException e) {
            GameAnnouncer.showMessage("File doesn't exist or save is corrupted");
            throw new RuntimeException("Parsing error");
        }




        return displayObjects;
    }
}

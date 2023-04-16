package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.graphics.GameFieldWall;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Walls implements Iterable<GameFieldWall> {
    List<GameFieldWall> walls;

    public Walls() {
        walls = new ArrayList<>();
    }
    @Override
    public Iterator<GameFieldWall> iterator() {
        return walls.iterator();
    }
}

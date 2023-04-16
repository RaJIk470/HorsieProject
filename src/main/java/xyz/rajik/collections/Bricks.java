package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.graphics.Brick;
import xyz.rajik.graphics.GameColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Bricks implements Iterable<Brick> {
    List<Brick> bricks;
    public Bricks() {
        bricks = new ArrayList<>();
    }

    public void generateBricks(int x, int y, int width, int height, int screenWidth, int count) {
        int gap = 5;
        int bricksInRow = (screenWidth - 2 * x) / width;
        int rows = (count + bricksInRow - 1) / bricksInRow;
        int brickCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < bricksInRow; j++) {
                if (brickCount == count) return;

                bricks.add(new Brick(x + j * width + gap, y + i * height + gap, width, height, new GameColor(233, 233, 233)));

                brickCount++;
            }
        }
    }

    @Override
    public Iterator<Brick> iterator() {
        return bricks.iterator();
    }
}

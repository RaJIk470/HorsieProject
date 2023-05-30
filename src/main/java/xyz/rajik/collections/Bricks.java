package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.graphics.Bonus;
import xyz.rajik.graphics.Brick;
import xyz.rajik.graphics.GameColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Data
public class Bricks implements Iterable<Brick> {
    List<Brick> bricks;
    public Bricks() {
        bricks = new ArrayList<>();
    }

    public void generateBricks(int x, int y, int width, int height, int screenWidth, int count) {
        Random random = new Random();
        int gap = 5;
        int bricksInRow = (screenWidth - 2 * x) / width;
        int rows = (count + bricksInRow - 1) / bricksInRow;
        int brickCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < bricksInRow; j++) {
                if (brickCount == count) return;

                Brick brick = new Brick(x + j * width + gap, y + i * height + gap, width, height, new GameColor(233, 233, 233));
                //if (random.nextInt() % 10 == 1)
                    brick.getBonusList().add(new Bonus(x + j * width + gap, y + i * height + gap, width, height, 5, new GameColor(133, 133, 133)));
                bricks.add(brick);

                brickCount++;
            }
        }
    }

    @Override
    public Iterator<Brick> iterator() {
        return bricks.iterator();
    }
}

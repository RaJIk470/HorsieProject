package xyz.rajik.graphics;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.Game;

import java.awt.*;

@Data
@NoArgsConstructor
public class GameFieldWall extends DisplayObject {
    private WallType wallType;
    public GameFieldWall(int x, int y, int width, int height, WallType wallType) {
        this.x = x;
        this.y = y;
        this.maxX = x + width;
        this.maxY = y + height;
        this.width = width;
        this.height = height;
        this.wallType = wallType;
    }
    @Override
    protected void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(20));
        g.drawRect(x, y, width, height);
    }

    @Override
    public void handleCollisionEvent(CollisionEvent event) {
        if (wallType == WallType.DEATH_WALL && event.displayObject instanceof Ball) {
            Game.game.handleEvent(new GameOverEvent());
        }
    }
}

package xyz.rajik.graphics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class GameFieldWall extends DisplayObject {
    public GameFieldWall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.maxX = x + width;
        this.maxY = y + height;
        this.width = width;
        this.height = height;
    }
    @Override
    protected void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(20));
        g.drawRect(x, y, width, height);
    }
}

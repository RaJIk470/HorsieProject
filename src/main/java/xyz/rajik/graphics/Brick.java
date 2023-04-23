package xyz.rajik.graphics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class Brick extends DisplayObject {
    boolean isBroken; // can u hear the silence can u see the dark can u fix the broken can u feel my heart

    public Brick(int x, int y, int width, int height, GameColor color) {
        this.x = x;
        this.y = y;
        this.maxX = x + width;
        this.maxY = y + height;
        this.width = width;
        this.height = height;
        this.gameColor = color;
        this.color = gameColor.toAwtColor();
        this.isBroken = false;
    }


    @Override
    protected void draw(Graphics2D g) {
        if (!isBroken) {
            g.setColor(gameColor.toAwtColor());
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(4));
            g.drawRect(x, y, width, height);
        }
    }
}

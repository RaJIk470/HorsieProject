package xyz.rajik.graphics;

import java.awt.*;

public class GameFieldWall extends DisplayObject {
    public GameFieldWall(double x, double y, double width, double height) {
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
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }
}

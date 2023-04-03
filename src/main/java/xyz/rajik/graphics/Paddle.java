package xyz.rajik.collections;

import xyz.rajik.graphics.DisplayObject;

import java.awt.*;

public class Paddle extends DisplayObject {
    @Override
    protected void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
}

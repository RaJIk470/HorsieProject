package xyz.rajik.graphics;

import lombok.Data;

import java.awt.*;

@Data
public class Ball extends DisplayObject {
    private Direction direction;
    private Double speed;
    private final double SPEED_MULTIPLIER = 100;
    public Ball(double x, double y, double width, double height, double speed, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
        direction = new Direction();
        direction.setUp();
        direction.setLeft();
    }
    public void move(double dt) {
        x += direction.getX() * speed * dt * SPEED_MULTIPLIER;
        y += direction.getY() * speed * dt * SPEED_MULTIPLIER;
    }
    @Override
    protected void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, (int)width, (int)height);
    }
}

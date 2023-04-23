package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class Ball extends DisplayObject implements Moveable {
    private Direction direction;
    private int speed;
    private static final double SPEED_MULTIPLIER = 40;
    public Ball(int x, int y, int width, int height, int speed, GameColor color) {
        this.x = x;
        this.y = y;
        this.maxX = width + x;
        this.maxY = height + y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.gameColor = color;
        this.color = color.toAwtColor();
        direction = new Direction();
        direction.setUp();
        direction.setRight();
    }
    public void move(double dt) {
        x += direction.getX() * speed * (dt * SPEED_MULTIPLIER);
        y += direction.getY() * speed * (dt * SPEED_MULTIPLIER);
        maxX = x + width;
        maxY = y + height;
    }

    @Override
    public void handleCollisionEvent(CollisionEvent event) {
        DisplayObject displayObject = event.displayObject;
        if (displayObject instanceof Brick) {
            Brick brick = (Brick) displayObject;
            if (brick.isBroken) return;
            brick.isBroken = true;
        }
        double x2 = displayObject.x;
        double y2 = displayObject.y;
        double maxX2 = displayObject.maxX;
        double maxY2 = displayObject.maxY;

        if (y < maxY2 && maxY > maxY2)
            direction.setDown();
        if (x < maxX2 && maxX > maxX2)
            direction.setRight();
        if (maxY > y2 && y < y2)
            direction.setUp();
        if (maxX > x2 && x < x2)
            direction.setLeft();
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(gameColor.toAwtColor());
        g.fillOval((int)x, (int)y, (int)width, (int)height);
    }
}
